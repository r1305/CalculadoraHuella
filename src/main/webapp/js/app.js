'use strict';

// Declare app level module which depends on filters, and services - uiGmapgoogle-maps, google-maps
var idicApp = angular.module('idic', ['ui.router', 'ngResource','uiGmapgoogle-maps',
    'idic.controllers', 'encuesta.controllers', 'idic.services', 'encuesta.services'
]);

idicApp.config(function($stateProvider, $urlRouterProvider) {
    // For any unmatched url, send to /accounts
    $urlRouterProvider.otherwise("/mapa")

    $stateProvider.

    // Mapa
    state('mapa', {
        url: '/mapa',
        templateUrl: 'partials/mapa/mapa.html',
        controller: 'MapaController'
    }).

    // Historico
    state('historico', {
        url: '/historico',
        templateUrl: 'partials/historico/historico.html',
        controller: 'HistoricoController'
    }).    

    // Encuesta
    state('encuesta', {
        url: '/encuesta',
        templateUrl: 'partials/encuesta/encuesta.html',
        controller: 'EncuestaController'
    }).

    // General
    state('general', {
        url: '/general',
        templateUrl: 'partials/encuesta/general.html',
        controller: 'DetalleEncuestaController'
    }).
    
    // Detalles Electricidad - Produccion CO2 Electricidad
    state('detallesElectricidad', {
        url: '/detallesElectricidad',
        templateUrl: 'partials/detallesElectricidad/detallesElectricidad.html',
        controller: 'DetallesElectricidadController'
    }).
    
    // Transporte
    state('transporte', {
        url: '/encuesta/transporte',
        templateUrl: 'partials/encuesta/transporte.html',
        controller: 'TransporteController'
    }).

    // Electricidad
    state('electricidad', {
        url: '/encuesta/electricidad',
        templateUrl: 'partials/encuesta/electricidad.html',
        controller: 'ElectricidadController'
    }).
    
    // Residuo Solido
    state('residuo', {
        url: '/encuesta/residuo',
        templateUrl: 'partials/encuesta/residuo.html',
        controller: 'ResiduoSolidoController'
    }).

    // Resultado Encuesta
    state('resultado', {
        url: '/encuesta/resultado?co2AnnoTransportes&co2AnnoElectricidad&co2AnnoResiduosSolidos',
        templateUrl: 'partials/encuesta/resultado.html',
        controller: 'ResultadoEncuestaController'
    }).

    // Detalle
    state('detalle', {
        url: '/detalle?status',
        templateUrl: 'partials/detalle.html',
        controller: 'DetalleController'
    });
});