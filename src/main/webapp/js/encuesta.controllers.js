var app = angular.module('encuesta.controllers', ['ui.grid', 'ui.grid.selection', 'ui.grid.edit','ui.grid.cellNav', 'ui.chart']);

app.controller('EncuestaController', ['$scope','$state', 'DistritoFactory','EncuestaFactory',
function($scope, $state, DistritoFactory, EncuestaFactory) {
	console.log('EncuestaController.01');
	$scope.distritos = DistritoFactory.query();
   	$scope.response = {};
	// Iniciar Encuesta
	$scope.iniciarEncuesta = function () {
		console.log('distrito:' + $scope.distrito);
		$scope.encuesta.distritoId = $scope.distrito.id;
   		EncuestaFactory.iniciarEncuesta($scope.encuesta).$promise.then(function(response) {
   			console.log(response)
   			$scope.response = response;
   			if ('OK' == response.resultado) {
   				$state.go('general', response);
   			}	
   		});
   	};
}
]);


/**
 * 02. General(Detalle)
 * 
 */
app.controller('DetalleEncuestaController', ['$scope','$state', 'i18nService', 'EncuestaFactory',
function($scope, $state, i18nService, EncuestaFactory) {
	console.log('DetalleEncuestaController.4');
  	$scope.detalleEncuesta = {
  			detalleEncuestaPersonaList : []
  	};

	// Grid
  	i18nService.setCurrentLang('es');
	var columnDefs = [
	   { name: "indicadorSexo", displayName: '', enableCellEdit:false},
	   { name: "1", displayName: 'Menos de 2 años', enableCellEdit: true, type: 'number' },
	   { name: "2", displayName: 'De 2 a 14 años', enableCellEdit: true, type: 'number' },
	   { name: "3", displayName: 'De 15 a 50 años', enableCellEdit: true, type: 'number' },
	   { name: "4", displayName: 'De 51 a 70 años', enableCellEdit: true, type: 'number' },
	   { name: "5", displayName: 'Más de 70 años', enableCellEdit: true, type: 'number' }
	];
	var data = [
	{"indicadorSexo": "Hombre", "1":null, "2":null, "3":null, "4":null, "5":null},
	{"indicadorSexo": "Mujer", "1":null, "2":null, "3":null, "4":null, "5":null}
	];
	$scope.gridOpts = {
		enableCellEditOnFocus:true,	
		columnDefs: columnDefs,
		data: data
	};

	// Agregar Detalle de Encuesta(Iformacion General)
  	$scope.agregarDetalleEncuesta = function () {
  		console.log('agregarDetalleEncuesta:');
  		console.log('personas:');
  		// Lista de personas
  		for (i = 0; i<2; i++) {
  			for (j = 1; j<=5; j++) {
  				console.log($scope.gridOpts.data[i]);
  				var cantidadPersonas = $scope.gridOpts.data[i][j];
  				console.log("cantidadPersonas["+i+"]["+j+"]:" + cantidadPersonas)
  				if (cantidadPersonas != null) {
  					var detallePersona = {
  						sexo : (i==0) ? 'H' : 'M',
  						indicadorEdad	: j,
  						cantidadPersonas:cantidadPersonas 
  					}
  					$scope.detalleEncuesta.detalleEncuestaPersonaList.push(detallePersona);
  				}
  			}
  		}
  		
  		EncuestaFactory.agregarDetalleEncuesta($scope.detalleEncuesta).$promise.then(function(response) {
  			console.log(response)
  			$state.go('transporte', response);
  		});
    };
}
]);


/**
 * Transporte
 */
app.controller('TransporteController', ['$scope','$state', 'ElementoFactory', 'EncuestaFactory',
function($scope, $state, ElementoFactory, EncuestaFactory) {
	$scope.transportes = ElementoFactory.query({sectorId:2});

	// Valores iniciales
	$scope.detalleTransporteList = [];
	
	// Grid
	var columnDefs = [
	                   { field: "categoria", displayName: 'Categoria Transporte' },
	                   { field: "transporte", displayName: 'Transporte' },
	                   { field: "cantidadViajes", displayName: 'Numero de Viajes por Semana' },
	                   { field: "tiempoUso", displayName: 'Tiempo de duracion (Minutos por Semana)' }
	                 ];
	var data = [];
	$scope.gridOpts = {
		    columnDefs: columnDefs,
		    data: data
		  };

	// Agregar Transporte
	$scope.ageregarTransporte = function(categoriaId, transporte) {
		var transporteSeleccionado = null;
		var categoria = "";
		
		switch(categoriaId) {
			case 1 : {
				transporteSeleccionado = $scope.transportePublicoSeleccionado;
				categoria = "Transporte Público";
				break;
			}
			case 2 : {
				transporteSeleccionado = $scope.transporteEmpresaSeleccionado;
				categoria = "Transporte Privado - Empresarial";
				break;
			}
			case 3 : {
				transporteSeleccionado = $scope.transportePropioSeleccionado;
				categoria = "Transporte Privado - Propio";
				break;
			}
		}

	    // Detalle Transporte para Base Datos
	    var detalleTransporte = {
				transporteXCategoriaId: transporteSeleccionado.id,
	    		cantidadViajesDia : transporte.cantidadViajesDia,
	    		tiempoUsoDia : transporte.tiempoUsoDia
	    };
		
		// Informacion para grilla
		var filaTransporte = {
	        "categoria": categoria,
	        "transporte": transporteSeleccionado.nombre,
	        "cantidadViajes": transporte.cantidadViajes,
	        "tiempoUso": transporte.tiempoUso
		}
		
		$scope.detalleTransporteList.push(detalleTransporte);
	    $scope.gridOpts.data.push(filaTransporte);
   	};
   	
	// Agregar Detalle Electricidad a Encuesta
	$scope.agregarEncuestaTransporte = function() {
		EncuestaFactory.agregarEncuestaTransporte($scope.detalleTransporteList).$promise.then(function(response) {
			$state.go('electricidad');
		});		  
	}
}
]);

/**
 * Electricidad
 */
app.controller('ElectricidadController', ['$scope','$state', 'CategoriaFactory', 'ElementoFactory', 'EncuestaFactory',
  function($scope, $state, CategoriaFactory, ElementoFactory, EncuestaFactory) {
  	$scope.dispositivos = ElementoFactory.query({sectorId:1});

	// Valores iniciales
	$scope.detalleElectricidadList = [];
  	
  	// Grid
  	var columnDefs = [
	   { field: "categoria", displayName: 'Categoria Electricidad' },
	   { field: "dispositivo", displayName: 'Iluminacion/Electrodomesticos/Equipos' },
	   { field: "cantidadDispositivos", displayName: 'Cantidad disponible en el hogar' },
	   { field: "tiempoUso", displayName: 'Tiempo de uso Diario/Semanal' }
	];
  	var data = [];
  	$scope.gridOpts = {
	    columnDefs: columnDefs,
	    data: data
	};

  	// Agregar Dispositivo Electrico
  	$scope.ageregarDispositivo = function(categoriaId, dispositivoElectrico) {
  		var dispositivoSeleccionado = null;
		var categoria = "";
		
		switch(categoriaId) {
			case 1 : {
				dispositivoSeleccionado = $scope.iluminacionSeleccionado;
				categoria = "Iluminación";
				break;
			}
			case 2 : {
				dispositivoSeleccionado = $scope.equipoContinuoSeleccionado;
				categoria = "Electrodomésticos/Equipos - Uso continuo";
				break;
			}
			case 3 : {
				dispositivoSeleccionado = $scope.equipoEsporadicoSeleccionado;
				categoria = "Electrodomésticos/Equipos - Uso esporádico";
				break;
			}
		}
		
	    // Detalle Transporte para Base Datos
	    var detalleElectricidad = {
    		dispositivoElectricoId: dispositivoSeleccionado.id,
    		cantidadDispositivos : dispositivoElectrico.cantidadDispositivos,
    		tiempoUsoDia : dispositivoElectrico.tiempoUsoDia
	    };
		
	    // Informacion para grilla
		var filaDispositivo = {
	        "categoria": categoria,
	        "dispositivo": dispositivoSeleccionado.nombre,
	        "cantidadDispositivos": dispositivoElectrico.cantidadDispositivos,
	        "tiempoUso": dispositivoElectrico.tiempoUso
		}
		
		$scope.detalleElectricidadList.push(detalleElectricidad);
	    $scope.gridOpts.data.push(filaDispositivo);
	};
 	
	  // Agregar Detalle Electricidad a Encuesta
	  $scope.agregarEncuestaElectricidad = function() {
		EncuestaFactory.agregarEncuestaElectricidad($scope.detalleElectricidadList).$promise.then(function(response) {
	        $state.go('residuo', response);
		});		  
	  }
}
]);

/**
 * Residuo Solido
 */
app.controller('ResiduoSolidoController', ['$scope','$state', 'CategoriaFactory', 'ElementoFactory', 'EncuestaFactory',
	function($scope, $state, CategoriaFactory, ElementoFactory, EncuestaFactory) {
		$scope.categoriasResiduos = CategoriaFactory.query({sectorId:3});
		$scope.residuos = ElementoFactory.query({sectorId:3});
		
		// Valores iniciales
		$scope.detalleResiduoList = [];
		$scope.categoriaSemanal = {id:-1};
		$scope.categoriaMensual = {id:-1};
		
		// Grid
		var columnDefs1 = [
		                   { name: 'Categoria' },
		                   { name: 'Residuo' }
		                 ];
		var columnDefs2 = [
		                   { name: 'Categoria' },
		                   { name: 'Residuo' }
		                 ];
		
		var data1 = [];
		var data2 = [];
		$scope.gridOpts = {
				 headerTemplate: '<div>Residuos Semanales</div>',
				showGridFooter: true,
				enableRowSelection: true,
				selectionRowHeaderWidth: 35,
			    columnDefs: columnDefs1,
			    data: data1
			  };
		$scope.gridOpts2 = {
				headerTemplate: '<div>Residuos Mensuales</div>',
			    columnDefs: columnDefs2,
			    data: data2
			  };
		
		// Agregar Residuo: Para ambos periodos: Semanal/Mensual
		$scope.ageregarResiduo = function(periodo, nuevoResiduo) {
			var categoriaSeleccionado = null;
			var residuoSeleccionado = null;
		    var filaResiduo = {};
		    
			switch(periodo) {
				case 1 : {
					categoriaSeleccionado = $scope.categoriaSemanalSeleccionado;
					residuoSeleccionado = $scope.residuoSemanalSeleccionado;
					break;
				}
				case 2 : {
					categoriaSeleccionado = $scope.categoriaMensualSeleccionado;
					residuoSeleccionado = $scope.residuoMensualSeleccionado;
					break;
				}
			}
		    
		    // Detalle Residuo para Base Datos
		    var detalleResiduo = {
		    		residuoSolidoId: residuoSeleccionado.id,
		    		cantidadResiduoGenerado : nuevoResiduo.cantidadResiduoGenerado	
		    };

		    
	    	var filaResiduo = {
	            "Categoria": categoriaSeleccionado.nombre,
	            "Residuo": residuoSeleccionado.nombre
	    	}

		    $scope.detalleResiduoList.push(detalleResiduo);
		    $scope.gridOpts.data.push(filaResiduo);
		    $scope.gridOpts2.data.push(filaResiduo);
		  };
		  
	  // Finalizar Encuesta
	  $scope.finalizar = function() {
		EncuestaFactory.finalizarEncuesta($scope.detalleResiduoList).$promise.then(function(response) {
			console.log('response:');
			console.log(response);
			$state.go('resultado', response);
		});		  
	  }
	}
]);

// Resultado de encuesta CO2 por Sector: (Grafico - Pie) / Consumo CO2 (Tacometro)
app.controller('ResultadoEncuestaController', ['$scope','$state', '$stateParams',
function($scope, $state, $stateParams) {
	console.log('$stateParams: ');
	console.log($stateParams);	
	var co2Anno = Number($stateParams.co2AnnoTransportes) + Number($stateParams.co2AnnoElectricidad) + Number($stateParams.co2AnnoResiduosSolidos);
	var resultadoEncuesta = {
		encuestaId : $stateParams.co2AnnoTransportes,
		co2AnnoTransportes: $stateParams.co2AnnoTransportes,
		co2AnnoElectricidad: $stateParams.co2AnnoElectricidad,
		co2AnnoResiduosSolidos: $stateParams.co2AnnoResiduosSolidos
	}
	/*
	var resultadoEncuesta = {
			encuestaId : 123,
			co2AnnoTransportes: 1.98,
			co2AnnoElectricidad: 2.54,
			co2AnnoResiduosSolidos: 2.34
		}
	*/
	console.log('resultadoEncuesta:');
	console.log(resultadoEncuesta);
	
	$scope.resultadoEncuestaDataPoints = [];

	var dataPoint1 = {
			name: 'Transporte',
		// label: 'Transporte',
			//label: 'Transporte',	
		//indexLabel: 'Transporte',
		y: Number(resultadoEncuesta.co2AnnoTransportes),
		//x: resultadoEncuesta.co2AnnoTransportes,
	}
	var dataPoint2 = {
			name: 'Electricidad',	
		//label: 'Electricidadaa',
		//indexLabel: 'Electricidad',
		y: Number(resultadoEncuesta.co2AnnoElectricidad),
		//x: resultadoEncuesta.co2AnnoElectricidad
	}
	var dataPoint3 = {
			name: 'Residuos Solidos',
		//label: 'Residuos Solidoaaas',
		//indexLabel: 'Residuos Solidos3',
		y: Number(resultadoEncuesta.co2AnnoResiduosSolidos),
		//x: resultadoEncuesta.co2AnnoResiduosSolidos,
	}
	$scope.resultadoEncuestaDataPoints.push(dataPoint1);
	$scope.resultadoEncuestaDataPoints.push(dataPoint2);
	$scope.resultadoEncuestaDataPoints.push(dataPoint3);
	
	// Chart
    $scope.chart = new CanvasJS.Chart("chartContainer", {
    	theme: "theme2",
    	/*
        title: {
            text: "Resultado Encuesta"      
        },
        */
        data: [
            
            {
            	/*
            	type: "pie",
            	// name: "Data nombre", 
                showInLegend: true,
                toolTipContent: "{y} - #percent %",
                // yValueFormatString: "#0.#,,. Million",
                legendText: "{indexLabel}",
                percentFormatString: "#0.##",
                // indexLabelPlacement: "inside",
                indexLabel: "#percent%",
                */
            	type: "pie",
    			indexLabelFontFamily: "Garamond",       
    			indexLabelFontSize: 20,
    			indexLabelFontWeight: "bold",
    			startAngle:0,
    			indexLabelFontColor: "MistyRose",       
    			indexLabelLineColor: "darkgrey", 
    			indexLabelPlacement: "inside", 
    			// toolTipContent: "{name}: {y}hrs",
    			toolTipContent: "{name}: {y} - #percent %",
    			showInLegend: true,
    			indexLabel: "#percent%",            	
                dataPoints: $scope.resultadoEncuestaDataPoints,
            },
        ]
    });
    $scope.chart.render();

    // jqplot: inicio
    console.log('jqplot:inicio.1');
    console.log('co2Anno:' + co2Anno);
    $scope.donutChartData = [[co2Anno]];
    $scope.donutChartOptions = {
    	// title: 'Produccion CO2 Anual',	
        seriesDefaults : {
            renderer : jQuery.jqplot.MeterGaugeRenderer,
            rendererOptions : {
            	label: 'Produccion CO2 Anual',
                labelPosition: 'bottom',
                labelHeightAdjust: -5,
                intervalOuterRadius: 95,
                showDataLabels : true,
                ticks: [0,1,2,3,4,5,6,7,8,9,10],
                intervals:[3.5,5,10],
                intervalColors:['#66cc66', '#E7E658', '#cc6666']
            }
        }
    };
    // jqplot: fin
	}
]);
