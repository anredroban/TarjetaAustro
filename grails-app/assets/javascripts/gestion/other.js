/**
 * Created by root on 08/06/17.
 */

$(function () {

    $("#datosExequial").hide();
    $("#nombresExequial").val("");
    $("#edadExequial").val("");
    $("#parentescoExequial").val($("#parentescoExequial option:first").val());
    $("#asistencia").val($("#asistencia option:last").val());

    $("#asistencia").change(function(){
        $("#nombresExequial").val("");
        $("#edadExequial").val("");
        $("#parentescoExequial").val($("#parentescoExequial option:first").val());
        $("#tipoCobro").val($("#tipoCobro option:first").val());
        if($("#asistencia").val()[0] == "AE" || $("#asistencia").val()[1] == "AE"){
            $("#datosExequial").show();
            $("#tipoCobroDiv").hide();
        }else{
            $("#datosExequial").hide();
            $("#tipoCobroDiv").show();
        }
        if($("#asistencia").val()[0] == "AT" || $("#asistencia").val()[1] == "AT"){
            $("#tipoCobroDiv").show();
        }else{
            $("#tipoCobroDiv").hide();
        }
    });

    $('#primerNombre').keyup(function(){
         var delta = this.value;
        $("#nombreTarjeta").val(delta);
         
    });
    $('#primerApellido').keyup(function(){
        var dev = this.value;
        //var dev = $("#nombreTarjeta").val(this.value);
        $("#nombreTarjeta").val($('#primerNombre').val()+" "+this.value);

    });
    $('input[name~="fechaNacimiento"]').change(function(){
        //alert($(this).val());

        var dateInput = $(this).val();
        var dateFrom = dateInput.getYear();
       // alert(dateFrom);
        // var d = new Date();
        // alert(d);
        // var output = d.getFullYear();
        // var stack = output - dateFrom;
        //alert(stack);
    });
    // $('#fechaNacimiento').keyup(function(){
    //     var d = new Date();
    //     var output = d.getFullYear();
    //     var dateFrom = date.getFullYear();
    //     alert(date);
    //     if(date.length > 0){
    //         // var month = d.getMonth();
    //         // var day = d.getDate();
    //         // var output = d.getFullYear();
    //         //var tr = output +'.'+month+'.'+ day
    //         alert('EMPTY');
    //     }else{
    //         var stack = output - dateFrom;
    //         alert(stack);
    //
    //     }
    // });

        function load_data(query){
        $.post(baseUrl + "/FuncionesAjax/searchUser", {
            query:query
        }).done(function (data) {

            //$('#result').html(data);
            if(data == 'null'){

            }else{
                alert('ya tienes este usuario en la base de datos con la misma cedula');
            }
        });
    }
    $('#cedula').keyup(function(){
        var query = $(this).val();
        if(query != ''){
            load_data(query);

        }else{
            load_data();
        }
    });

    $('#send').click(function(e){
        var status = $("#status option:selected").html()
        var substatus = $("#substatus option:selected").html()

        if( $('#tipoCobroDiv').is(":visible") ){
            if($('#tipoCobro').val() == ''){
                alert("Ingrese el tipo de cobro");
                e.preventDefault();
                return false;
            }
        }

        if($("#datosExequial").is(":visible")){
            if($("#nombresExequial").val() == ''){
                alert("Campo nombres en Asistencia Exequial vacío");
                e.preventDefault()
                return false
            }
            if($("#edadExequial").val() == ''){
                alert("Campo edad en Asistencia Exequial vacío");
                e.preventDefault()
                return false
            }
            if($("#parentescoExequial").val() == ''){
                alert("Campo parentesco en Asistencia Exequial vacío");
                e.preventDefault()
                return false
            }
        }

        if (substatus == 'CU1 ACEPTA PRODUCTO CAMPAÑA'){
            valitadeCharecters()
        }else if (substatus == 'CU2 ACEPTA VENTA CRUZADA'){
            $("form[name='management-client']").submit();
            // valitadeCharecters()
        }else if (substatus == 'CU3 ACEPTA PRODUCTO Y VENTA CRUZADA'){
            valitadeCharecters()
        }else if(status == 'NO CONTACTADO'){
            $("form[name='management-client']").submit();
        }else {
            $("form[name='management-client']").submit();
        }



    });

})


function valitadeCharecters() {
    var provinciaDomicilio = $("#provinciaDomic option:selected").html()
    var ciudadDomicilio = $("#ciudadDomic option:selected").html()
    var sectorDomicilio = $("#sectorDomic").val();
    var callePrincipalDomicilio = $("#callePrincipalDomic").val();
    var numeracionDomicilio = $("#numeracionDomic").val();
    var calleTransversalDomicilio = $("#calleTransversalDomic").val();

    var referenciaDomicilio = $("#referenciaDomic").val();
    var provinciaTrabajo = $("#provinciaTrab option:selected").html()
    var ciudadTrabajo = $("#ciudadTrab").val();
    var sectorTrabajo = $("#sectorTrab").val();
    var callePrincipalTrabajo = $("#callePrincipalTrab").val();
    var numeracionTrabajo = $("#numeracionTrab").val();
    var calleTransversalTrabajo = $("#calleTransversalTrab").val();
    var referenciaTrabajo = $("#referenciaTrab").val();
    var celularContacto = $("#celularContacto").val();
    var telefonoDomicilioContacto = $("#telefonoDomContacto").val();
    var telefonoTrabContacto = $("#telefonoTrabContacto").val();
    $infoAddres = provinciaDomicilio + ' ' + ciudadDomicilio + ' ' + sectorDomicilio + ' ' + callePrincipalDomicilio + ' ' + numeracionDomicilio
        + ' ' + calleTransversalDomicilio + ' ' + referenciaDomicilio;
    $infoWork = provinciaTrabajo + ' ' + ciudadTrabajo + ' ' + sectorTrabajo + ' ' + callePrincipalTrabajo
        + ' ' + numeracionTrabajo + ' ' + calleTransversalTrabajo + ' ' + referenciaTrabajo;
    $infoWork = replaceMore2Spaces($infoWork);
    $infoAddres = replaceMore2Spaces($infoAddres);
    var mobileNumber = replaceMore2Spaces(celularContacto);
    var telefonoDomicilio = replaceMore2Spaces(telefonoDomicilioContacto);
    var contactWork = replaceMore2Spaces(telefonoTrabContacto);

    if ($infoAddres.length > 150 || $infoAddres.length < 100) {
        alert("El total de la dirección de domicilio tiene " + $infoAddres.length + " caracteres. El número de caracteres permitido es de 100 a 150.");

    }else if($infoWork.length > 150 || $infoWork.length < 100){
        alert("El total de la dirección de Trabajo tiene " + $infoWork.length + " caracteres. El número de caracteres permitido es de 100 a 150.");

    }else if(mobileNumber.length < 10){
        alert("Celular Contacto tiene " + mobileNumber.length + " numeros y son 10 digitos para que sea valido.");

    }else if( mobileNumber.length > 10){
        alert("Celular Contacto tiene " + mobileNumber.length + " numeros y son 10 digitos para que sea valido.");

    }else if(telefonoDomicilio.length < 9 ){
        alert("Tlf Contacto Domicilio tiene  " + telefonoDomicilio.length + " numeros y son 9 digitos para que sea valido.");

    }else if(telefonoDomicilio.length > 9 ){
        alert("Tlf Contacto Domicilio tiene  " + telefonoDomicilio.length + " numeros y son 9 digitos para que sea valido.");

    }else if(contactWork.length < 9){
        alert("Tlf Contacto Trabajo tiene  " + contactWork.length + " numeros y son 9 digitos para que sea valido.");

    }else if(contactWork.length > 9){
        alert("Tlf Contacto Trabajo tiene  " + contactWork.length + " numeros y son 9 digitos para que sea valido.");

    }else if(!$.isNumeric(mobileNumber)){
        alert("Celular Contacto solo se acepta numeros ");

    }else if(!$.isNumeric(telefonoDomicilio)){
        alert("Numero de telefono del Casa solo se acepta numeros");
    }else if(!$.isNumeric(contactWork)){
        alert("Numero de telefono del trabajo solo se acepta numeros");

    }else{
        $("form[name='management-client']").submit();
    }
}
function replaceMore2Spaces(text) {
    var resultText = text;
    while (true) {
        if (resultText.indexOf("  ") != -1) {
            resultText = resultText.replace(/\s{2,}/, " ");
        }else{
            if (resultText.indexOf(".") != -1) {
                resultText = resultText.replace('.', "");
            }else{
                if (resultText.indexOf(",") != -1) {
                    resultText = resultText.replace(/,{2,}/, "");
                }else{
                    break;
                }
            }
        }
    }
    return resultText.trim();
}

