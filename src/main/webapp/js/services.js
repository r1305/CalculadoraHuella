'use strict';
/**
 * Services:
 * Consume the web service
 **/
var services = angular.module('idic.services', ['ngResource']);

services.factory('MapaFactory', function($resource) {
    return $resource('servicios/mapas/', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    })
});

services.factory('HistoricoFactory', function($resource) {
    // return $resource('servicios/historicos?distritoId=1280&sectorId=1', {}, {
	return $resource('servicios/historicos', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    })
});

services.factory('DetallesElectricidadFactory', function($resource) {
	return $resource('servicios/detallesElectricidad', {}, {
        query: {
            method: 'GET',
            isArray: true
        }
    })
});
