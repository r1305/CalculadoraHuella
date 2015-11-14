var services = angular.module('encuesta.services', ['ngResource']);

services.factory('EncuestaFactory', function($resource) {
    return $resource('encuestas/', {}, {
        iniciarEncuesta: {
            method: 'PUT',
            url: "encuestas/encuesta"
        },
        agregarDetalleEncuesta: {
            method: 'POST',
            url: "encuestas/detalle"
        },
        agregarEncuestaTransporte: {
            method: 'POST',
            url: "encuestas/transporte"
        },
        agregarEncuestaElectricidad: {
            method: 'POST',
            url: "encuestas/electricidad"
        },
    	finalizarEncuesta: {
    		method: 'POST',
    		url: "encuestas/encuesta"
    	},
    	resultadoEncuesta: {
    		method: 'POST',
    		url: "encuestas/resultado"
    	}        
    })
});

/**
 * Servicio de ista de distritos
 */
services.factory('DistritoFactory', function($resource) {
	return $resource('servicios/distritos/', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    })
});

/**
 * Servicio de Categorias segun sector
 */
services.factory('CategoriaFactory', function($resource) {
	return $resource('servicios/sectores/:sectorId/categorias', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    })
});

/**
 * Servicio de Elementos segn sector
 */
services.factory('ElementoFactory', function($resource) {
	return $resource('servicios/sectores/:sectorId/elementos', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    })
});
