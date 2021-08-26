$(document).ready(function(){
    $identificacionTitular = $("#identificacionTitular");
    $status = $("#status");
    $substatus = $("#substatus");
    $subSubStatus = $("#subSubStatus");
    $recall = $("#recall");
    $provinciaDomic = $("#provinciaDomic");
    $ciudadDomic = $("#ciudadDomic");
    $sectorDomic = $("#sectorDomic");
    $callePrincipalDomic = $("#callePrincipalDomic");
    $numeracionDomic = $("#numeracionDomic");
    $calleTransversalDomic = $("#calleTransversalDomic");
    $tipoVivienda = $("#tipoVivienda");
    $referenciaDomic = $("#referenciaDomic");
    $provinciaTrab = $("#provinciaTrab");
    $ciudadTrab = $("#ciudadTrab");
    $sectorTrab = $("#sectorTrab");
    $callePrincipalTrab = $("#callePrincipalTrab");
    $numeracionTrab = $("#numeracionTrab");
    $calleTransversalTrab = $("#calleTransversalTrab");
    $tipoTrab = $("#tipoTrab");
    $referenciaTrab = $("#referenciaTrab");
    $entrega = $("#entrega");
    $nombreContacto = $("#nombreContacto");
    $rangoVisita = $("#rangoVisita");
    $facturacion = $("#facturacion");
    $estadoCtaDigital = $("#estadoCtaDigital");
    $seguroDesgravamen = $("#seguroDesgravamen");
    $telefonoDomContacto = $("#telefonoDomContacto");
    $telefonoTrabContacto = $("#telefonoTrabContacto");
    $celularContacto = $("#celularContacto");
    $recallDiv = $("#recallDiv");
    $subSubStatusDiv = $("#subSubStatusDiv");
    $productoPrincipalDiv = $("#productoPrincipalDiv");
    $productoSecundarioDiv = $("#productoSecundarioDiv");
    $recallDiv = $("#recallDiv");
    $subSubStatusDiv = $("#subSubStatusDiv");
    $asistencia = $("#asistencia");
    $nombresExequial = $("#nombresExequial");
    $edadExequial = $("#edadExequial");
    $parentescoExequial = $("#parentescoExequial");
    $datosExtraTotal = $("#datosExtraTotal");
    $datosExequial = $("#datosExequial");
    $tipoCobroDiv = $("#tipoCobroDiv");
    $btnGuardarAdicional = $("#btnGuardarAdicional");
    $tipoIdentificacion = $("#tipoIdentificacion");
    $cedula = $("#cedula");
    $primerNombre = $("#primerNombre");
    $segundoNombre = $("#segundoNombre");
    $primerApellido = $("#primerApellido");
    $segundoApellido = $("#segundoApellido");
    $nombreTarjeta = $("#nombreTarjeta");
    $cupoOtorgado = $("#cupoOtorgado");
    $sexo = $("#sexo");
    $estadoCivil = $("#estadoCivil");
    $cedulaConyugue = $("#cedulaConyugue");
    $efechaNacimientoConyugue = $("#fechaNacimientoConyugue");



    $parentesco = $("#parentesco");
    $fechaNacimiento = $("#fechaNacimiento");
    $nacionalidad = $("#nacionalidad");
    //$provinciaNacimiento = $("#provinciaAdic");
    //$ciudadNacimiento = $("#ciudadAdic");
    $observaciones = $("#observaciones");
    $bodyTablaAdicionales = $("#bodyTablaAdicionales");
    $nacionalidadSelect = $("#nacionalidadSelect");
    $nacionalidadDiv = $("#nacionalidadDiv");
    $btnGuardarGestion = $("#btnGuardarGestion");
    $debitoAsistencia = $("#debitoAsistencia");
    $datos247 = $("#datos247");
    $cobertura247 = $("#cobertura247");
    $tipoCobro247 = $("#tipoCobro247");
    $tipoCobro = $("#tipoCobro");
    $telefonoContactado = $("#telefonoContactado");
    $telefonoContactadoDiv = $("#telefonoContactadoDiv");

    $datosConyugue = $("#datosConyugue");
    $fechaNacimientoConyugue = $("#fechaNacimientoConyugue");
    $nombreEmpresaTrabajo = $("#nombreEmpresaTrabajo");
    $estadoCivil = $("#estadoCivil");
    $tipoVivienda = $("#tipoVivienda");
    $divEstimado = $("#divEstimado");
    $sucursal = $("#sucursal");
    $oficina = $("#oficina");

    $provinciaAdic = $("#provinciaAdic");
    $ciudadAdic = $("#ciudadAdic");
    $relacionLaboral = $("#relacionLaboral");
    $telefonoCelular = $("#telefonoCelular");
    $operadoraAdicional = $("#operadoraAdicional");
    $correo = $("#correo");
    $viveFamiliares = $("#viveFamiliares");
    $valDireccionAdicional = $("#valDireccionAdicional");
    $direccionAdicional = $("#direccionAdicional");



    $adicionalesArray = [];
    $tdDelete = "<td><a href='#' class='linkDelete' onclick='return false'><i class='fa fa-trash'></i> Eliminar</a></td>";

    init();

    //Cuando se cambia el ESTADO
    $status.change(function(){
        esconderCamposEstados();
        $telefonoContactadoDiv.hide();
        $telefonoContactado.val("");
        //$cupoOtorgado.val("%");
        if($status.val() == ""){
            emptySelect('substatus');
        }else{
            $statusId = this.value;
            if($statusId == 1){
                $telefonoContactadoDiv.show();
            }
            $.get(baseUrl + "/FuncionesAjax/getSubStatusByStatus", {
                id: $statusId
            }, function (data) {
                fillSelect('substatus', data);
            });
        }
    });


    //Cuando cambia el SUBESTADO
    $substatus.change(function(){
        esconderCamposEstados();
        if($substatus.val() == ""){
            emptySelect('subSubStatus');
        }else{
            $subStatusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/getSubSubStatusBySubStatus", {
                id: $subStatusId
            }, function (data) {
                if(fillSelect('subSubStatus', data) > 0){
                    $subSubStatusDiv.show();
                }else{
                    $("#subSubStatusDiv").hide();
                }
                if(data[2] == 'Rellamada'){ //Tipo de subestado
                    $("#recallDiv").show();
                }else{
                    $("#recallDiv").hide();
                }
                if(data[3]){ //Si el estado habilita la gestión del producto principal
                    $productoPrincipalDiv.show();
                }else{
                    $productoPrincipalDiv.hide();
                }
                if(data[4]){ //Si el estado habilita la gestión del producto secundario
                    $productoSecundarioDiv.show();
                }else{
                    $productoSecundarioDiv.hide();
                }
            });
        }
    });

    //Cuando cambia el SUBSUBESTADO
    $("#subSubStatus").change(function () {
        //esconderCamposEstados();
        if($("#subSubStatus").val() == ""){
            emptySelect("motivosSubSubEstados");
        }else{
            $motivosubStatusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/geMotivoSubStatusBySubSubStatus", {
                id: $motivosubStatusId
            }, function (data) {
                if(fillSelect('motivosSubSubEstados', data) > 0){
                    $("#motivosSubSubEstadosDiv").show();
                }else{
                    $("#motivosSubSubEstadosDiv").hide();
                }
            });
        }
    });

    //Cuando cambia el TIPO DE ASISTENCIA
    $asistencia.change(function(){
        $datosExequial.hide();
        $datosExtraTotal.hide();
        $datos247.hide();
        if($asistencia.val().toString().indexOf("AE") != -1){
            $datosExequial.show();
        }else{
            $nombresExequial.val("");
            $edadExequial.val("");
            $parentescoExequial.val($("#parentescoExequial option:first").val());
        }
        if($asistencia.val().toString().indexOf("AT") != -1){
            $datosExtraTotal.show();
        }else{
            $tipoCobro.val($("#tipoCobro option:first").val());
        }
        if($asistencia.val().toString().indexOf("A247") != -1){
            $datos247.show();
        }else{
            $cobertura247.val($("#cobertura247 option:first").val());
            $tipoCobro247.val($("#tipoCobro247 option:first").val());
        }
    });

    //Cuando cambia la PROVINCIA DE DOMICILIO
    $provinciaDomic.change(function(){
        if($provinciaDomic.val() == ""){
            emptySelect('ciudadDomic');
            emptySelect('sectorDomic');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadDomic', data);
            });
        }
    });

    //Cuando se cambia la CIUDAD DE DOMICILIO
    $ciudadDomic.change(function () {
        if($ciudadDomic.val() == ""){
            emptySelect('sectorDomic');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquias", {id: $id}, function (data) {
                fillSelect('sectorDomic', data);
            });
        }
    });

    //Cuando cambia la PROVINCIA DE TRABAJO
    $provinciaTrab.change(function(){
        if($provinciaTrab.val() == ""){
            emptySelect('ciudadTrab');
            emptySelect('sectorTrab');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadTrab', data);
            });
        }
    });

    $sucursal.change(function(){
        if($sucursal.val() == ""){
            emptySelect('oficina');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getOficinas", {id: $id}, function (data) {
                fillSelect('oficina', data);
            });
        }
    });

    //Cuando se cambia la CIUDAD DE TRABAJO
    $ciudadTrab.change(function () {
        if($ciudadTrab.val() == ""){
            emptySelect('sectorTrab');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquias", {id: $id}, function (data) {
                fillSelect('sectorTrab', data);
            });
        }
    });

    //Cuando cambia la PROVINCIA DE ADICIONAL
    $provinciaAdic.change(function(){
        if($provinciaAdic.val() == ""){
            emptySelect('ciudadAdic');
            /*emptySelect('sectorDomic');*/
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadAdic', data);
            });
        }
    });


    //CUANDO CAMBIA EL ESTADO CIVIL
    $estadoCivil.change(function(){
        if($estadoCivil.val() == "CASADO"){
            $datosConyugue.show();
        }else{
            $datosConyugue.hide();
        }
    });


    //CUANDO CAMBIA EL VIVE CON FAMILIARES
    $viveFamiliares.change(function(){
        if($viveFamiliares.val() == "NO"){
            $valDireccionAdicional.show();
        }else{
            $valDireccionAdicional.hide();
        }
    });

    //CUANDO CAMBIA EL TIPO DE VIVIENDA
    $tipoVivienda.change(function(){
        if($tipoVivienda.val() == "P" || $tipoVivienda.val() == "N"){
            $divEstimado.show();
        }else{
            $divEstimado.hide();
        }
    });

    //Cuando se da clic en AGREGAR EN EL MODAL DEL ADICIONAL
    $btnGuardarAdicional.click(function (e) {
        //Se hace las validaciones de rutina
        if(!validarDatosAdicional()){
            e.preventDefault()
            return false
        }

        //Se valida adicionales repetidos
        if(getAdicionalByCedula($cedula.val().trim()) != null){
            alert("Ya ingresó un adicional con número de cédula "+$cedula.val().trim());
            e.preventDefault();
            return false;
        }

        $adicional = new Adicional( $cedula.val().trim(), $tipoIdentificacion.val(), $primerNombre.val(), $segundoNombre.val(), $primerApellido.val(), $segundoApellido.val(),
        $nombreTarjeta.val(), $cupoOtorgado.val(), $sexo.val(), $estadoCivil.val(), $parentesco.val(), $fechaNacimiento.val(), $nacionalidadSelect.val(),
        $provinciaAdic.val(), $ciudadAdic.val(), $relacionLaboral.val(), $telefonoCelular.val(), $operadoraAdicional.val(), $correo.val(), $viveFamiliares.val(),
        $direccionAdicional.val(), $cedulaConyugue.val(), $fechaNacimientoConyugue.val(), $observaciones.val());
        $adicionalesArray.push($adicional);
        mostrarAdicionalesEnTabla();
        limpiarCamposAdicional();
    });

    //Cuando se va a ELIMINAR UN ADICIONAL
    $bodyTablaAdicionales.on("click", ".linkDelete", function () {
        $adicionalABorrar = $(this).parent().parent().attr("id");
        if(confirm("Se eliminará el adicional de cédula "+$adicionalABorrar)){
            borrarAdicional($adicionalABorrar);
        }
    });


    //Se hace que el NOMBRE TARJETA se inicialice con Primer Nombre y Primer apellido
    $primerNombre.keyup(function(){
        var delta = this.value;
        $nombreTarjeta.val(delta);

    });
    $primerApellido.keyup(function(){
        var dev = this.value;
        $nombreTarjeta.val($primerNombre.val()+" "+this.value);

    });


    //Cuando se cambia la NACIONALIDAD en el Modal de Adicional
    $nacionalidadSelect.change(function () {
        $nacionalidad.val($nacionalidadSelect.val());
        if($nacionalidadSelect.val() == "ECUATORIANA"){
            $nacionalidadDiv.show();
        }else{
            $nacionalidadDiv.hide();
        }
    });

    //Cuando se da clic en GUARDAR GESTION
    $btnGuardarGestion.click(function (e) {
        if(!validateManagementData()){
            e.preventDefault()
            return false
        }else{
            $btnGuardarGestion.hide();
        }
    });
});

//This function empties a select component
function emptySelect(idSelect) {

    var select = document.getElementById(idSelect);
    var option = document.createElement('option');
    var NumberItems = select.length;

    while (NumberItems > 0) {
        NumberItems--;
        select.remove(NumberItems);
    }

    option.text = '-- Seleccione --';
    option.value = ''
    select.add(option, null);
}

//This function fills a select component
function fillSelect(idSelect, data) {

    var select = document.getElementById(idSelect);
    var numberSubstatus = data[0].length;

    emptySelect(idSelect)

    if (numberSubstatus > 0) {
        for (var i = 0; i < numberSubstatus; i++) {
            var option = document.createElement('option');
            option.text = data[1][i];
            option.value = data[0][i];
            select.add(option, null);
        }
    }

    return numberSubstatus;
}

function esconderCamposEstados(){
    $recallDiv.hide();
    $subSubStatusDiv.hide();
    $productoPrincipalDiv.hide();
    $productoSecundarioDiv.hide();
    $datosConyugue.hide();
    $divEstimado.hide();

    $("#motivosSubSubEstadosDiv").hide();

    $("#datosExequial").hide();
    $datos247.hide();
    $("#nombresExequial").val("");
    $("#edadExequial").val("");
    $("#parentescoExequial").val($("#parentescoExequial option:first").val());
    $debitoAsistencia.val($("#debitoAsistencia option:first").val());
    $tipoCobro.val($("#tipoCobro option:first").val());
    $cobertura247.val($("#cobertura247 option:first").val());
    $tipoCobro247.val($("#tipoCobro247 option:first").val());
    $("#asistencia").val($("#asistencia option:last").val());
}

function init(){
    esconderCamposEstados();
    $status.val($("#status option:first").val());
    $provinciaDomic.val($("#provinciaDomic option:first").val());
    $provinciaTrab.val($("#provinciaTrab option:first").val());
    $nacionalidadDiv.hide();
    //$nacionalidadSelect.val($("#nacionalidadSelect option:first").val());

    $telefonoContactadoDiv.hide();
    $telefonoContactado.val("");
    $valDireccionAdicional.hide();
}

function mostrarAdicionalesEnTabla(){
    $bodyTablaAdicionales.html("");
    for(var i = 0; i < $adicionalesArray.length; i++){
        $contenidoTr = createTd($adicionalesArray[i].cedula)+createTd($adicionalesArray[i].tipoIdentificacion)+createTd($adicionalesArray[i].primerNombre+" "+$adicionalesArray[i].segundoNombre+" "+
                $adicionalesArray[i].primerApellido+" "+$adicionalesArray[i].segundoApellido)+createTd($adicionalesArray[i].nombreTarjeta+
            createTd($adicionalesArray[i].cupoOtorgado)+createTd($adicionalesArray[i].sexo)+createTd($adicionalesArray[i].estadoCivil)+createTd($adicionalesArray[i].parentesco)+
            createTd($adicionalesArray[i].fechaNacimiento)+createTd($adicionalesArray[i].nacionalidad)/*+createTd($adicionalesArray[i].provinciaNacimiento+" - "+$adicionalesArray[i].ciudadNacimiento)*/+$tdDelete);
        $bodyTablaAdicionales.append(createTr($adicionalesArray[i].cedula, $contenidoTr));
    }
    $("#hidenAdicionales").val(JSON.stringify($adicionalesArray));
}

function createTd($content){
    return '<td>' + $content + '</td>';
}

function createTr($id, $content){
    return '<tr id="'+$id+'">' + $content + '</tr>';
}

function getAdicionalByCedula($cedula){
    $adicionalEncontrado = null;
    for(var i = 0; i < $adicionalesArray.length; i++){
        if($adicionalesArray[i].cedula == $cedula){
            $adicionalEncontrado = $adicionalesArray[i];
            break;
        }
    }
    return $adicionalEncontrado;
}

function borrarAdicional($cedula) {
    for(var i = 0; $adicionalesArray.length; i++){
        if($adicionalesArray[i].cedula === $cedula){
            $adicionalesArray.splice(i, 1);
            break;
        }
    }
    mostrarAdicionalesEnTabla();
}

function validateManagementData(){
    $isValid = true;

    if ($("#number1").is(":visible")) {
        if ($("#estadoTel1").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 1");
            $isValid = false;
            return
        }
    }
    if ($("#number2").is(":visible")) {
        if ($("#estadoTel2").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 2");
            $isValid = false;
            return
        }
    }
    if ($("#number3").is(":visible")) {
        if ($("#estadoTel3").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 3");
            $isValid = false;
            return
        }
    }
    if ($("#number4").is(":visible")) {
        if ($("#estadoTel4").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 4");
            $isValid = false;
            return
        }
    }
    if ($("#number5").is(":visible")) {
        if ($("#estadoTel5").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 5");
            $isValid = false;
            return
        }
    }
    if ($("#number6").is(":visible")) {
        if ($("#estadoTel6").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 6");
            $isValid = false;
            return
        }
    }
    if ($("#number7").is(":visible")) {
        if ($("#estadoTel7").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 7");
            $isValid = false;
            return
        }
    }
    if ($("#number8").is(":visible")) {
        if ($("#estadoTel8").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 8");
            $isValid = false;
            return
        }
    }
    if ($("#number9").is(":visible")) {
        if ($("#estadoTel9").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 9");
            $isValid = false;
            return
        }
    }
    if ($("#number10").is(":visible")) {
        if ($("#estadoTel10").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 10");
            $isValid = false;
            return
        }
    }

    if($("#status").val() == ""){
        alert("Debe escoger un estado");
        $isValid = false;
        return
    }else{
        if($("#substatus").val() == ""){
            alert("Debe escoger un subestado");
            $isValid = false
            return
        }else{
            if($("#recallDiv").is(":visible")){
                if($("#agendamiento").val() == ""){
                    alert("Campo agendamiento vacio")
                    $isValid = false;
                    return
                }
                if($("#recall").val() == ""){
                    alert("Debe ingresar una fecha para la rellamada")
                    $isValid = false;
                    return
                }else{
                    if (calcularDias($("#recall").val()) > 3){
                        alert("La fecha de rellamada no puede sobrepasar los tres días")
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#subSubStatus").is(":visible")){
                if($("#subSubStatus").val() == ""){
                    alert("Debe escoger un sub subestado")
                    $isValid = false;
                    return
                }
            }
            if($("#motivosSubSubEstadosDiv").is(":visible")){
                if($("#motivosSubSubEstados").val() == ""){
                    alert("Debe escoger un motivo SubSubestado");
                    $isValid = false;
                    return
                }
            }
            if($telefonoContactadoDiv.is(":visible")){
                if($telefonoContactado.val().trim() === ""){
                    alert("Ingrese el teléfono al cual pudo contactar al cliente");
                    $isValid = false;
                    return
                }else{
                    if($telefonoContactado.val().substring(0, 1) != 0){
                        alert("El teléfono contactado es incorrecto");
                        $isValid = false;
                        return
                    }else{
                        if(!validarSiNumero($telefonoContactado.val())){
                            alert("El teléfono contactado no es un número válido");
                            $isValid = false;
                            return
                        }
                    }
                }
            }
        }
    }

    if($productoPrincipalDiv.is(":visible")){
        if($provinciaDomic.val() == ""){
            alert("Ingrese la provincia de domicilio");
            $isValid = false;
            return;
        }
        if($ciudadDomic.val() == ""){
            alert("Ingrese la ciudad de domicilio");
            $isValid = false;
            return;
        }
        if($sectorDomic.val() == ""){
            alert("Ingrese la parroquia de domicilio");
            $isValid = false;
            return;
        }
        if($callePrincipalDomic.val() == ""){
            alert("Ingrese la calle principal de domicilio");
            $isValid = false;
            return;
        }
        if($numeracionDomic.val() == ""){
            alert("Ingrese la numeración de domicilio");
            $isValid = false;
            return;
        }
        if($calleTransversalDomic.val() == ""){
            alert("Ingrese la calle transversal de domicilio");
            $isValid = false;
            return;
        }
        if($tipoVivienda.val() == ""){
            alert("Ingrese el tipo de vivienda de domicilio");
            $isValid = false;
            return;
        }
        if($referenciaDomic.val() == ""){
            alert("Ingrese la referencia de domicilio");
            $isValid = false;
            return;
        }

        if($provinciaTrab.val() == ""){
            alert("Ingrese la provincia de trabajo");
            $isValid = false;
            return;
        }
        if($ciudadTrab.val() == ""){
            alert("Ingrese la ciudad de trabajo");
            $isValid = false;
            return;
        }
        if($sectorTrab.val() == ""){
            alert("Ingrese la parroquia de trabajo");
            $isValid = false;
            return;
        }
        if($callePrincipalTrab.val() == ""){
            alert("Ingrese la calle principal de trabajo");
            $isValid = false;
            return;
        }
        if($numeracionTrab.val() == ""){
            alert("Ingrese la numeración de trabajo");
            $isValid = false;
            return;
        }
        if($calleTransversalTrab.val() == ""){
            alert("Ingrese la calle transversal de trabajo");
            $isValid = false;
            return;
        }
        if($tipoTrab.val() == ""){
            alert("Ingrese el tipo de locación de trabajo");
            $isValid = false;
            return;
        }
        if($referenciaTrab.val() == ""){
            alert("Ingrese la referencia de trabajo");
            $isValid = false;
            return;
        }

        if($entrega.val() == ""){
            alert("Ingrese el lugar de entrega");
            $isValid = false;
            return;
        }
        if($nombreContacto.val() == ""){
            alert("Ingrese el nombre de contacto");
            $isValid = false;
            return;
        }else{
            if(!validarSiSoloLetras($nombreContacto.val())){
                alert("El nombre de contacto sólo debe tener letras, sin números ni tildes");
                $isValid = false;
                return;
            }
        }
        if($rangoVisita.val() == ""){
            alert("Ingrese el rango de visita");
            $isValid = false;
            return;
        }
        if($facturacion.val() == ""){
            alert("Ingrese la facturación");
            $isValid = false;
            return;
        }
        if($estadoCtaDigital.val() == ""){
            alert("Ingrese el estado de cuenta digital");
            $isValid = false;
            return;
        }
        if($seguroDesgravamen.val() == ""){
            alert("Campo Seguro Desgravamen vacio");
            $isValid = false;
            return;
        }
        if($telefonoDomContacto.val() == ""){
            alert("Ingrese el teléfono de domicilio del contacto");
            $isValid = false;
            return;
        }else{
            if(!validarSiNumero($telefonoDomContacto.val())){
                alert("El dato ingresado como teléfono de domicilio de contacto no es válido");
                $isValid = false;
                return;
            }else{
                if($telefonoDomContacto.val().length != 9){
                    alert("El teléfono de domicilio de contacto debe tener 9 dígitos.");
                    $isValid = false;
                    return;
                }else{
                    if($telefonoDomContacto.val().substr(0,1) != '0'){
                        alert("Valor incorrecto en el número de domicilio de contacto");
                        $isValid = false;
                        return;
                    }
                }
            }
        }
        if($telefonoTrabContacto.val() == ""){
            alert("Ingrese el teléfono de trabajo del contacto");
            $isValid = false;
            return;
        }else{
            if(!validarSiNumero($telefonoTrabContacto.val())){
                alert("El dato ingresado como teléfono de trabajo de contacto no es válido");
                $isValid = false;
                return;
            }else{
                if($telefonoTrabContacto.val().length != 9){
                    alert("El teléfono de trabajo de contacto debe tener 9 dígitos.");
                    $isValid = false;
                    return;
                }else{
                    if($telefonoTrabContacto.val().substr(0,1) != '0'){
                        alert("Valor incorrecto en el número de trabajo de contacto");
                        $isValid = false;
                        return;
                    }
                }
            }
        }
        if($celularContacto.val() == ""){
            alert("Ingrese el teléfono celular del contacto");
            $isValid = false;
            return;
        }else{
            if(!validarSiNumero($celularContacto.val())){
                alert("El dato ingresado como teléfono celular de contacto no es válido");
                $isValid = false;
                return;
            }else{
                if($celularContacto.val().length != 10){
                    alert("El teléfono celular de contacto debe tener 10 dígitos.");
                    $isValid = false;
                    return;
                }else{
                    if($celularContacto.val().substr(0,2) != '09'){
                        alert("Valor incorrecto en el número celular de contacto");
                        $isValid = false;
                        return;
                    }
                }
            }
        }

        //Se valida que se haya ingresado adicionales
        if($adicionalesArray.length == 0){
            alert("Debe ingresar los dicionales del cliente");
            $isValid = false;
            return;
        }

        //Se valida que la información concatenada no exceda los 150 caracteres
        $informacionConcatenadaDomicilio = $callePrincipalDomic.val() + " " + $numeracionDomic.val() + " " + $calleTransversalDomic.val()+" " + $sectorDomic.val() + " " + $referenciaDomic.val();
        $informacionConcatenadaTrabajo = $callePrincipalTrab.val() + " " + $numeracionTrab.val() + " " + $calleTransversalTrab.val() + " " + $sectorTrab.val() + " " + $referenciaTrab.val();
        $longitudDomicilio = $informacionConcatenadaDomicilio.length;
        $longitudTrabajo = $informacionConcatenadaTrabajo.length

        if($longitudDomicilio > 150){
            alert("La longitud en total de la dirección de domicilio tiene "+$longitudDomicilio+" caracteres. Sólo se permite hasta 150");
            $isValid = false;
            return;
        }

        if($longitudTrabajo > 150){
            alert("La longitud en total de la dirección de trabajo tiene "+$longitudDomicilio+" caracteres. Sólo se permite hasta 150");
            $isValid = false;
            return;
        }
        if($nombreEmpresaTrabajo.val() == ""){
            alert("Campo nombre empresa de Trabajo vacío");
            $isValid = false;
            return;
        }

    }


    return $isValid;
}

function formatearCedulaTitular($cedula){
    if($cedula.length == 9){
        $cedula = '0' + $cedula;
    }else{
        if($cedula.length > 10){
            $cedula = $cedula.substr($cedula.length-10, 10);
        }
    }
    return $cedula;
}

function validarDatosAdicional(){
    $isValid = true;
    if($cedula.val() == ""){
        alert("Ingrese el número de cédula del adicional");
        $isValid = false;
        return;
    }else{
        if($nacionalidadSelect.val() == "ECUATORIANA"){
            if(!validarSiNumero($cedula.val())){
                alert("El número de cédula ingresado no es válido");
                $isValid = false;
                return;
            }else{
                if(!esCedulaValida($cedula.val())){
                    alert("El número de cédula del adicional no es correcto(No pasa la validación).");
                    $isValid = false;
                    return;
                }else{
                    if(formatearCedulaTitular($identificacionTitular.val()) === $cedula.val()){
                        alert("No puede ingresar la cédula del titular como cédula del adicional.");
                        $isValid = false;
                        return;
                    }
                }
            }
        }
    }
        if($tipoIdentificacion.val() == ""){
            alert("ingrese el tipo de identificación del adicional");
            $isValid = false;
            return;
        }

        if($primerNombre.val() == ""){
        alert("ingrese el primer nombre del adicional");
        $isValid = false;
        return;
    }else{
        if(!validarSiSoloLetras($primerNombre.val())){
            alert("El primer nombre debe sólo contener letras, sin números ni tildes");
            $isValid = false;
            return;
        }
    }
    if($segundoNombre.val() == ""){
        // alert("ingrese el segundo nombre del adicional");
        // $isValid = false;
        // return;
    }else{
        if(!validarSiSoloLetras($segundoNombre.val())){
            alert("El segundo nombre debe sólo contener letras, sin números ni tildes");
            $isValid = false;
            return;
        }
    }
    if($primerApellido.val() == ""){
        alert("ingrese el primer apellido del adicional");
        $isValid = false;
        return;
    }else{
        if(!validarSiSoloLetras($primerApellido.val())){
            alert("El primer apellido debe sólo contener letras, sin números ni tildes");
            $isValid = false;
            return;
        }
    }
    if($segundoApellido.val() == ""){
        // alert("ingrese el segundo apellido del adicional");
        // $isValid = false;
        // return;
    }else{
        if(!validarSiSoloLetras($segundoApellido.val())){
            alert("El segundo apellido debe sólo contener letras, sin números ni tildes");
            $isValid = false;
            return;
        }
    }
    if($nombreTarjeta.val() == ""){
        alert("ingrese el nombre en la tarjeta del adicional");
        $isValid = false;
        return;
    }else{
        if(!validarSiSoloLetras($nombreTarjeta.val())){
            alert("El nombre en tarjeta debe sólo contener letras, sin números ni tildes");
            $isValid = false;
            return;
        }else{
            if(!$nombreTarjeta.val().includes($primerApellido.val())){
                alert("El nombre en la tarjeta debe contener el primer apellido");
                $isValid = false;
                return;
            }else{
                if($nombreTarjeta.val().length > 19){
                    alert("El nombre en la tarjeta no puede sobrepasar los 19 caracteres");
                    $isValid = false;
                    return;
                }
            }
        }
    }
    if($cupoOtorgado.val() == ""){
        alert("Ingrese el cupo otorgado");
        $isValid = false;
        return;
    }else{
        if(!validarSiNumero($cupoOtorgado.val())){
            alert("El dato ingresado en el cupo a otorgar no es un número válido");
            $isValid = false;
            return;
        }else{
            if($cupoOtorgado.val() < 200){
                alert("El cupo no puede ser menos de $200 ");
                $isValid = false;
                return;
            }
        }
    }
    if($sexo.val() == ""){
        alert("Ingrese el género del adicional");
        $isValid = false;
        return;
    }
    if($estadoCivil.val() == ""){
        alert("Ingrese el estado civil del adicional");
        $isValid = false;
        return;
    }
    if($datosConyugue.is(":visible")){
        if($cedulaConyugue.val() == ""){
            alert("Ingrese la cédula del conyugue del adicional");
            $isValid = false;
            return;
        }else{
            if(!esCedulaValida($cedulaConyugue.val())){
                alert("El número de cédula del conyugue del adicional no es correcto (No pasa la validación).");
                $isValid = false;
                return;
            }
        }
        if($fechaNacimientoConyugue.val() == ""){
            alert("Ingrese la fecha de nacimiento del conyugue del adicional");
            $isValid = false;
            return;
        }else{
            if(!existeFecha($fechaNacimientoConyugue.val())){
                alert("El formato de la fecha de Nacimiento del conyugue es dd/mm/aaaa");
                $isValid = false;
                return;
            }
        }
    }
    if($parentesco.val() == ""){
        alert("Ingrese el parentesco");
        $isValid = false;
        return;
    }
    if($relacionLaboral.val() == ""){
        alert("ingrese la relación laboral del adicional");
        $isValid = false;
        return;
    }
    if($fechaNacimiento.val() == ""){
        alert("Ingrese la fecha de nacimiento");
        $isValid = false;
        return;
    }else{
        if(calcularEdad($fechaNacimiento.val()) < 16){
            alert("No se puede agregar el adicional a personas menores de 16 años");
            $isValid = false;
            return;
        }else{
            if(!existeFecha($fechaNacimiento.val())){
                alert("El formato de la fecha de Nacimiento es dd/mm/aaaa");
                $isValid = false;
                return;
            }
        }
    }
    if($nacionalidadSelect.val() == ""){
        alert("Seleccione la nacionalidad del adicional");
        $isValid = false;
        return;
    }
    if($nacionalidadDiv.is(":visible")){
        if($provinciaAdic.val() == ""){
            alert("Debe ingresar la Provincia de Nacimiento del Adicional")
            $isValid = false;
            return
         }
        if($ciudadAdic.val() == ""){
            alert("Debe ingresar la Ciudad de Nacimiento del Adicional")
            $isValid = false;
            return
        }
    }
    if($telefonoCelular.val() == ""){
        alert("Ingrese el teléfono celular del adicional");
        $isValid = false;
        return;
    }else{
        if(!validarSiNumero($telefonoCelular.val())){
            alert("El dato ingresado como teléfono celular de adicional no es válido");
            $isValid = false;
            return;
        }else{
            if($telefonoCelular.val().length != 10){
                alert("El teléfono celular de adicional debe tener 10 dígitos.");
                $isValid = false;
                return;
            }else{
                if($telefonoCelular.val().substr(0,2) != '09'){
                    alert("Valor incorrecto en el número celular de adicional");
                    $isValid = false;
                    return;
                }
            }
        }
    }
    if($operadoraAdicional.val() == ""){
        alert("Ingrese la operadora del adicional");
        $isValid = false;
        return;
    }
    if($correo.val() == ""){
        alert("Ingrese el correo del adicional");
        $isValid = false;
        return;
    }else{
        if(!validarEmail($correo.val())){
            alert("El Email de Adicional ingresado es incorrecto.");
            $isValid = false;
            return;
        }
    }
    if($viveFamiliares.val() == ""){
        alert("Campo vive con familiares vacío");
        $isValid = false;
        return;
    }
    if($valDireccionAdicional.is(":visible")){
        if($direccionAdicional.val() == ""){
            alert("Ingrese la dirección de domicilio del Adicional")
            $isValid = false;
            return
        }
    }

    return $isValid;
}

/**
 * Función basada en función de internet: https://gist.github.com/vickoman/7800717
 */
function esCedulaValida($entrada){
    $esValida = true;
    if($entrada.length != 10){
        $esValida = false;
    }else{
        //Los dos primeros dígitos me indican la provincia
        $region = parseInt($entrada.substring(0, 2));
        if ($region <= 0 || $region > 30){
            $esValida = false;
        }else{
            $ultimoDigito = $entrada.substring(9, 10);
            //Saco los pares y los sumo
            $pares = parseInt($entrada.substring(1,2)) + parseInt($entrada.substring(3,4)) + parseInt($entrada.substring(5,6)) + parseInt($entrada.substring(7,8));

            //Agrupo los impares, los multiplico por un factor de 2, si la resultante es > que 9 le restamos el 9 a la resultante
            $numero1 = $entrada.substring(0,1);
            $numero1 = ($numero1 * 2);
            if( $numero1 > 9 ){ $numero1 = ($numero1 - 9); }

            $numero3 = $entrada.substring(2,3);
            $numero3 = ($numero3 * 2);
            if( $numero3 > 9 ){ $numero3 = ($numero3 - 9); }

            $numero5 = $entrada.substring(4,5);
            $numero5 = ($numero5 * 2);
            if( $numero5 > 9 ){ $numero5 = ($numero5 - 9); }

            $numero7 = $entrada.substring(6,7);
            $numero7 = ($numero7 * 2);
            if( $numero7 > 9 ){ $numero7 = ($numero7 - 9); }

            $numero9 = $entrada.substring(8,9);
            $numero9 = ($numero9 * 2);
            if( $numero9 > 9 ){ $numero9 = ($numero9 - 9); }

            $impares = $numero1 + $numero3 + $numero5 + $numero7 + $numero9;

            //Suma total
            $suma_total = ($pares + $impares);


            //extraemos el primero digito
            if($suma_total >= 10) //Si la suma total es de dos cifras
                $primer_digito_suma = String($suma_total).substring(0,1);
            else
                $primer_digito_suma = '0';


            //Obtenemos la decena inmediata
            $decena = (parseInt($primer_digito_suma) + 1)  * 10;


            //Obtenemos la resta de la decena inmediata - la suma_total esto nos da el digito validador
            $digito_validador = $decena - $suma_total;

            //Si el digito validador es = a 10 toma el valor de 0
            if($digito_validador == 10)
                $digito_validador = 0;

            if($digito_validador != $ultimoDigito){
                $esValida = false;
            }
        }
    }

    return $esValida;
}

/**
 * Función bajada de internet https://es.stackoverflow.com/questions/31373/obtener-la-edad-a-partir-de-la-fecha-de-nacimiento-con-javascript-y-php
 * @param fecha
 * @returns {number}
 */
function calcularEdad(fecha) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}

/**
 * Valida si el valor ingresado es numérico
 * @param numero
 */
function validarSiNumero(numero){
    $esNumero = true;
    if (!/^([0-9])*$/.test(numero)){
        $esNumero = false;
    }
    return $esNumero;
}

/**
 * Valida que en el valor ingresado sólo hayan letras y espacios
 * @param entrada
 * @returns {boolean}
 */
function validarSiSoloLetras(entrada){
    $esSoloTexto = true;
    $filtro = /^([A-Za-z ])*$/;
    if(!$filtro.test(entrada)){
        $esSoloTexto = false;
    }
    return $esSoloTexto;
}

/**
 * Función tomada como ejemplo de internet https://www.lawebdelprogramador.com/foros/JavaScript/1594805-Calcular-la-cantidad-de-dias-entre-dos-fechas-Javascript-y-HTML.html
 * @param fecha
 * @returns {contdias}
 * @author Andres Redrobán
 * @description La siguiente función calcula el numero de dias tomando como referencia el facha actual y la fecha ingresada desde el sistema.
 */
function calcularDias(fecha){
    var fechaini = new Date();
    var fechafin = new Date(fecha);
    var diasdif= fechafin.getTime()-fechaini.getTime();
    var contdias = Math.round(diasdif/(1000*60*60*24));
    return contdias;
}

/**
 * Valida si el correo ingresado es correcto
 * @param email
 * @author Andres Redroban
 */
function validarEmail(email)
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}

/**
 * Funcion que valida si el formato de la fecha ingresada se encuentra en formato dd/mm/aaaa
 * @param Cadena
 * @author Andres Redroban
 */
function existeFecha(Cadena){
    var Fecha= new String(Cadena)
    var RealFecha= new Date()
    var Ano= new String(Fecha.substring(Fecha.lastIndexOf("/")+1,Fecha.length))
    var Mes= new String(Fecha.substring(Fecha.indexOf("/")+1,Fecha.lastIndexOf("/")))
    var Dia= new String(Fecha.substring(0,Fecha.indexOf("/")))

    // Valido el año
    if (isNaN(Ano) || Ano.length<4 || parseFloat(Ano)<1900){
        return false;
    }
    // Valido el Mes
     if (isNaN(Mes) || parseFloat(Mes)<1 || parseFloat(Mes)>12){
        return false; }
        // Valido el Dia
     if (isNaN(Dia) || parseInt(Dia, 10)<1 || parseInt(Dia, 10)>31){
         return false;
     }
     if (Mes==4 || Mes==6 || Mes==9 || Mes==11) { if (Dia>30) {
         return false;
     }
     }
     if (Mes==2 && Dia > 28 ) {
         bisiesto=((Ano % 4 == 0) && ((Ano % 100 != 0) || (Ano % 400 == 0)));
         if (bisiesto && Dia<=29)
             return true;
         else return false;
     } return true;
}


/**
 * Limpia los campos luego de agregar un adicional
 */
function limpiarCamposAdicional(){
    $tipoIdentificacion.val("%")
    $cedula.val("");
    $primerNombre.val("");
    $segundoNombre.val("");
    $primerApellido.val("");
    $segundoApellido.val("");
    $nombreTarjeta.val("");
    $cupoOtorgado.val("");
    $sexo.val($("#sexo option:first").val());
    $estadoCivil.val($("#estadoCivil option:first").val());
    $parentesco.val($("#parentesco option:first").val());
    $provinciaAdic.val($("#parentesco option:first").val());
    $ciudadAdic.val($("#parentesco option:first").val());
    $telefonoCelular.val("");
    $operadoraAdicional.val($("#parentesco option:first").val());
    $correo.val("");
    $viveFamiliares.val($("#parentesco option:first").val());
    $direccionAdicional.val("");
    $relacionLaboral.val("");
    $fechaNacimiento.val("");
    $nacionalidadSelect.val($("#nacionalidadSelect option:first").val());
    $nacionalidad.val("");
    $cedulaConyugue.val("");
    $fechaNacimientoConyugue.val("");
    $nacionalidadDiv.hide();
    $observaciones.val("");
}