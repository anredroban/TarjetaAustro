$(document).ready(function(){
//	DateRangePicker
	function cb(start, end) {
        $('#reportrange span').html(start.format('YYYY/MM/DD') + ' - ' + end.format('YYYY/MM/DD'));
    }
    cb(moment().subtract(29, 'days'), moment());

    $('#reportrange').daterangepicker({
        ranges: {
           'Hoy': [moment(), moment()],
           'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Últimos 7 días': [moment().subtract(6, 'days'), moment()],
           'Últimos 30 días': [moment().subtract(29, 'days'), moment()],
		   'Esta semana': [moment().startOf('week').add(1, 'days'), moment().endOf('week').add(1, 'days')],
           'Este mes': [moment().startOf('month'), moment().endOf('month')],		   
           'Mes Anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
		locale: {     
	    "format": 'YYYY/MM/DD',
        "separator": " - ",
        "applyLabel": "Aplicar",
        "cancelLabel": "Cancelar",        
        "customRangeLabel": "Elegir Rango",
        "daysOfWeek": [
            "Do",
            "Lu",
            "Ma",
            "Mi",
            "Ju",
            "Vi",
            "Sa"
        ],
        "monthNames": [
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
        ],
        "firstDay": 1
    }
		
    }, cb);    
    
    
    $("#btnreporteestados").click(function(){
    	var fechas = $("#reportrange").val().split('-');
    	var fechaInicio = fechas[0].replace('/', '-').trim().replace('/', '-') + " 00:00:00";
    	var fechaFin = fechas[1].replace('/', '-').trim().replace('/', '-') + " 23:59:59";
    	var nombreBase = $("#nombrebase").val();
    	var usuario = $("#usuario").val();    	
    	
    	$.get("/CallCenter/funcionesAjax/getGraficoEstadosReporte"
    			,{fechaInicio: fechaInicio, fechaFin: fechaFin, nombreBase: nombreBase, usuario: usuario}
    			, function(data){
    				var detalleContactados = new Array()
    				var detalleNoContactados = new Array();
    				var contadorContactados = 0;
    				var contadorNoContactados = 0;
    				
    				if(data['Contactado'] || data['NoContactado']){
    					
    					var datoContactados = 0;
    					var datoNoContactados = 0;
    					
    					for(var k in data['DetalleContactados']){			
    						detalleContactados[contadorContactados] = [k, data['DetalleContactados'][k]];
    						contadorContactados++;			
    					}
    					
    					for(var k in data['DetalleNoContactados']){			
    						detalleNoContactados[contadorNoContactados] = [k, data['DetalleNoContactados'][k]];
    						contadorNoContactados++;			
    					}
    					
    					if(data['Contactado']){
    						datoContactados = data['Contactado']; 
    					}
    					
    					if(data['NoContactado']){
    						datoNoContactados = data['NoContactado'];
    					}
    				
    				
    				$('#container').highcharts({
    			        chart: {
    			            type: 'pie'
    			        },
    			        title: {
    			            text: 'Contactados VS No Contactados'
    			        },
    			        subtitle: {
    			            text: 'Click en un area del pie para ver detalles'
    			        },
    			        plotOptions: {
    			            series: {
    			                dataLabels: {
    			                    enabled: true,
    			                    format: '{point.name}: {point.y:.0f}'
    			                }
    			            }
    			        },

    			        tooltip: {
    			            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    			            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b><br/>'
    			        },
    			        series: [{
    			            name: 'Brands',
    			            colorByPoint: true,
    			            data: [{
    			                name: 'Contactados',
    			                y: datoContactados,
    			                drilldown: 'Contactados'
    			            }, {
    			                name: 'No Contactados',
    			                y: datoNoContactados,
    			                drilldown: 'No Contactados'
    			            }]
    			        }],
    			        drilldown: {
    			            series: [{
    			                name: 'Contactados',
    			                id: 'Contactados',
    			                data: detalleContactados
    			            }, {
    			                name: 'No Contactados',
    			                id: 'No Contactados',
    			                data: detalleNoContactados
    			            }]
    			        }
    			    });
    				}else{
    					$('#container').html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>");
    				}
    			});
    });
    
    
});