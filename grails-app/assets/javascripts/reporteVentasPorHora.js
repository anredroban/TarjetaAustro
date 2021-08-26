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
    
    
    $("#btnreporteventasporhora").click(function(){
    	var fechas = $("#reportrange").val().split('-');
    	var fechaInicio = fechas[0].replace('/', '-').trim().replace('/', '-') + " 00:00:00";
    	var fechaFin = fechas[1].replace('/', '-').trim().replace('/', '-') + " 23:59:59";
    	var nombreBase = $("#nombrebase").val();
    	var usuario = $("#usuario").val();    	
    	
    	$.get("/CallCenter/funcionesAjax/getGraficoVentasHoraReporte"
    			,{fechaInicio: fechaInicio, fechaFin: fechaFin, nombreBase: nombreBase, usuario: usuario}
    			, function(data){
    				
    				
    				
    				if(Object.keys(data).length > 0){
    					
    					var categorias = new Array();
    					var dataVentas = new Array();
    					var dataContactados = new Array();
    					var contador = 0;
    					
    					for(var k in data){
    						categorias[contador] = Object.keys(data)[contador];
    						dataContactados[contador] = data[k]['contactados'];
    						dataVentas[contador] = data[k]['ventas'];
    						contador++;
    						
    					}
    					
    					
    					$('#container').highcharts({
    				        chart: {
    				            type: 'column'
    				        },
    				        title: {
    				            text: 'Ventas y Contactos por Hora'
    				        },
    				        subtitle: {
    				            text: 'Netlife'
    				        },
    				        xAxis: {
    				            categories: categorias,
    				            crosshair: true,
    				            title: {
    				                text: 'Horas'
    				            }
    				        },
    				        yAxis: {
    				            min: 0,
    				            title: {
    				                text: 'Cantidad'
    				            }
    				        },
    				        tooltip: {
    				            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
    				            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
    				                '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
    				            footerFormat: '</table>',
    				            shared: true,
    				            useHTML: true
    				        },
    				        plotOptions: {
    				            column: {
    				                pointPadding: 0.2,
    				                borderWidth: 0
    				            }
    				        },
    				        series: [{
    				            name: 'Contactados',
    				            data: dataContactados

    				        }, {
    				            name: 'Ventas',
    				            data: dataVentas

    				        }]
    				    });
    					
    					
    				}else{
    					$('#container').html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>");
    				}
    				
    				
    	});
    });
    
    
});