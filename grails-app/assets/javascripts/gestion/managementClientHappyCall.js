function init() {
    $("#status").val($("#status option:first").val());
    $("#subStatusDiv").hide();
    $("#recallDiv").hide();
    $("#managementData, #managementDataWork, #managementDataDelivery").hide();
    $("#btnAdicional").hide();
    $("#subSubStatusDiv").hide();
    $("#motivosNoAcepta").hide();
    $("#motivosNoAcepta1").hide();
    $("#motivosNoAcepta2").hide()

}

$(document).ready(function () {

    init();

    $("#status").change(function () {

        $("#subStatusDiv").hide();
        $("#recallDiv").hide();

        if ($("#status").val() == "") {
            emptySelect('substatus');
        } else {
            $statusId = this.value;

            $.get(baseUrl + "/Gestion/getSubStatusByStatus", {
                id: $statusId
            }, function (data) {

                if (fillSelect('substatus', data) > 0) {
                    $("#subStatusDiv").show();
                }

                if (data[2] == 'Rellamada') {
                    $("#recallDiv").show();
                }

                $('#substatus').on('change', function () {
                    $("#datosExequial").hide();
                    $("#asistencia").val($("#asistencia option:last").val());
                    if (getShowItem($(this).val(), data[3]) == true) {
                        $("#btnAdicional").show();
                        $("#managementData, #managementDataWork, #managementDataDelivery").show();
                    }else{
                        $("#btnAdicional").hide();
                        $("#managementData, #managementDataWork, #managementDataDelivery").hide();
                    }

                    if($(this).val() == 2 || $(this).val() == 3){
                        $("#subSubStatusDiv").show();
                    }else{
                        $("#subSubStatusDiv").hide();
                    }

                    if($(this).val() == 5){
                        $("#motivosNoAcepta").show();
                    }else{
                        $("#motivosNoAcepta").hide();
                    }

                    if($(this).val() == 8){
                        $("#motivosNoAcepta1").show();
                    }else{
                        $("#motivosNoAcepta1").hide();
                    }

                    if($(this).val() == 9){
                        $("#motivosNoAcepta2").show();
                    }else{
                        $("#motivosNoAcepta2").hide();
                    }

                    if($(this).val() == 4){
                        $("#recallDiv").show();
                    }else{
                        $("#recallDiv").hide();
                    }
                })
            });
        }

        if($(this).val() == '' || $(this).val() == '2'){
            $("#btnAdicional").hide();
            $("#managementData").hide();
        }
    })

    $("#submitManage").click(function (e) {
        validate(e);
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
        for (i = 0; i < numberSubstatus; i++) {
            var option = document.createElement('option');
            option.text = data[1][i];
            option.value = data[0][i];
            select.add(option, null);
        }
    }

    return numberSubstatus;
}

/**
 * Esteban Preciado
 * @param id
 * @param array
 * @returns {*}
 */
function getShowItem(id, array) {
    return array[id-1];
}

//Validations
function validate(e) {
    if ($("#status").val() == "") {
        alert("Debe seleccionar un estado");
        e.preventDefault();
        return false;
    }
    if ($("#subStatusDiv").is(":visible")) {
        if ($("#substatus").val() == "") {
            alert("Debe seleccionar un Subestado");
            e.preventDefault();
            return false;
        }
    }
    if ($("#recallDiv").is(":visible")) {
        if ($("#recall").val() == "") {
            alert("Debe llenar la fecha de rellamada");
            e.preventDefault();
            return false;
        }
    }
    if ($("#managementData").is(":visible")) {
        if ($("#questionService").val() == "") {
            alert("Pregunta 1 vacía");
            e.preventDefault();
            return false;
        }
        if ($("#questionProblem").val() == "") {
            alert("Pregunta 2 vacía");
            e.preventDefault();
            return false;
        }
        if ($("#questionTaxi").val() == "") {
            alert("Pregunta 3 vacía");
            e.preventDefault();
            return false;
        }
    }
}