'use strict';

/**
 * Controllers:
 **/

var app = angular.module('idic.controllers', []);

// Clear browser cache (in development mode)
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function() {
        // $templateCache.removeAll();
    });
});

/**
 * MapaController:
 **/
// Mapa
app.controller('MapaController', ['$scope', 'MapaFactory',
    function($scope, MapaFactory) {
	// add map with base parameters
  	$scope.map = {
	    center: {
	        latitude: -11.9773882,
	        longitude: -76.5422734        
	    },
	    zoom: 10,
  	};

  	// add markers for each location on the loaded tour
    $scope.markers = [];
    
    // function to create an individual marker
    $scope.createMarker = function(location) {
      var esCorrecto = location.co2Anno < 5 ? true : false;
      var escala = 10;
      if (location.co2Anno < 4 || location.co2Anno > 5.6) {
    	  escala = 20;
      }
      var marker = {
        idKey: location.id,
        coords: {
          latitude: location.latitud,
          longitude: location.longitud
        },
        options: {
      	label: '.',
          icon: {
          	path: google.maps.SymbolPath.CIRCLE,
          	// Verde
          	 fillColor: location.co2Anno < 5 ? '#006100': '#b40404',
          	 fillOpacity: 0.3,
          	 scale: escala,
          	 // Negro
          	 strokeColor: '#001300',
          	 strokeWeight: 1
          }
      }
      };
      return marker;
    };
    
    // function to fill array of markers
    $scope.createMarkers = function() {
      var locations2 = $scope.locations2;
      for (var i = 0; i < locations2.length; i++) {
        var marker = $scope.createMarker(locations2[i]);
        $scope.markers.push(marker);
      }
    };
    
	MapaFactory.query().$promise.then(function (locations) {
		$scope.locations2 = locations;
		$scope.createMarkers();
	});
}
]);

// Historico y Pronostico (Grafico - Linea)
app.controller('HistoricoController', ['$scope', 'HistoricoFactory', 
	function($scope, HistoricoFactory) {
	HistoricoFactory.query({distritoId:1280, sectorId:1}).$promise.then(function (historicoElectricidad) {
		$scope.historicoElectricidad = historicoElectricidad;
		HistoricoFactory.query({distritoId:1280, sectorId:3}).$promise.then(function (historicoResiduos) {
			$scope.historicoResiduos = historicoResiduos;
			
			$scope.historicoElectricidadDataPoints = [];
			$scope.historicoResiduosDataPoints = [];

			for (var i = 0 ; i < $scope.historicoElectricidad.length; i ++) {
				var historico = $scope.historicoElectricidad[i];
				var dataPoint = {
						label : historico.anno,
						x: new Date(historico.anno, 0),
						y : historico.co2Anno
						
				}
				$scope.historicoElectricidadDataPoints.push(dataPoint);
			}
			console.log("11:" + $scope.historicoElectricidadDataPoints);
			for (var i = 0 ; i < $scope.historicoResiduos.length; i ++) {
				var historico = $scope.historicoResiduos[i];
				var dataPoint = {
						label : historico.anno,
						x: new Date(historico.anno, 0),
						y : historico.co2Anno
						
				}
				$scope.historicoResiduosDataPoints.push(dataPoint);
			}
			console.log("22:" + $scope.historicoResiduosDataPoints);
			
			// Chart
		    $scope.chart = new CanvasJS.Chart("chartContainer", {
		        title: {
		            text: "San Juan de Lurigancho - Informacion Historica"      
		        },
		        axisY: {
		            title: "Toneladas CO2"
		        },
		        axisX: {
		        	title: "Anho"
		        },
		        data: [              
		            {
		            	name: "Historico Electricidad", 
		                type: "line",
		                // TODO
		                // lineDashType: "dot",
		                dataPoints: $scope.historicoElectricidadDataPoints,
		                showInLegend: true  
		            },
		            {
		            	name : "Historico Residuos Solidos",
		                type: "line",
		                dataPoints: $scope.historicoResiduosDataPoints,
		                markerBorderThickness: 5,
		                markerBorderColor: "green",
		                showInLegend: true
		            }
		        ],
		        legend:{
					verticalAlign: "bottom",
					horizontalAlign: "center",
					fontSize: 15,
					fontFamily: "Lucida Sans Unicode"
				},
		          legend: {
		              cursor:"pointer",
		              itemclick : function(e) {
		                if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		                e.dataSeries.visible = false;
		                }
		                else {
		                  e.dataSeries.visible = true;
		                }
		                $scope.chart.render();
		              }
		          }    
		    });
		    $scope.chart.render();
		});
	});
}
]);

// Detalles de Produccion CO2 por Dispositivo Electrico (Grafico - Barras)
app.controller('DetallesElectricidadController', ['$scope', 'DetallesElectricidadFactory', 
function($scope, DetallesElectricidadFactory) {
	DetallesElectricidadFactory.query({distritoId:1280}).$promise.then(function (detallesElectricidad) {
	$scope.detallesElectricidad = detallesElectricidad;
	$scope.detallesElectricidadDataPoints = [];

	console.log($scope.detallesElectricidad);
	for (var i = 0 ; i < $scope.detallesElectricidad.length; i ++) {
		var dispositivoElectrico = $scope.detallesElectricidad[i];
		var dataPoint = {
			label: dispositivoElectrico.nombre,
			y: dispositivoElectrico.co2Anno,
			// x: dispositivoElectrico.nombre
		}
		$scope.detallesElectricidadDataPoints.push(dataPoint);
	}
	
	// Chart
    $scope.chart = new CanvasJS.Chart("chartContainer", {
    	// width: 2000,// 500
        title: {
            text: "Produccion de CO2 - Electricidad"      
        },
        axisY: {
            title: "CO2 Anual(Toneladas)"
        },
        axisX: {
        	title: "Dispositivo Electrico",
        	labelAngle: 90,
        	labelAutoFit: true
        },
        data: [ 
            {
            	name: "Historico Electricidad", 
                type: "column",
                dataPoints: $scope.detallesElectricidadDataPoints,
            },
        ]
    });
    $scope.chart.render();
   	});
   }
]);


/**
 * DetalleController
 * 
 **/
app.controller('DetalleController', [
    '$scope',
    '$state',
    '$stateParams',
    function($scope, $state, $stateParams) {
        $scope.success = $stateParams.status == 'OK';
    }
]);
