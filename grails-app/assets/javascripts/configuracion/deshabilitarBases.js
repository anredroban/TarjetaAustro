$habiles = $("#habiles");
$inhabilitar = $("#inhabilitar");
$habilitar = $("#habilitar");
$noHabiles = $("#noHabiles");
$overlay = $("#overlay");

$overlay.hide();

$inhabilitar.click(function (e) {
    if(!$habiles.val()){
        alert("No ha seleccionado bases para inhabilitar");
        e.preventDefault();
        return false
    }
    $overlay.show();
    $bases = $habiles.val().toString();
    $.post(baseUrl + "/FuncionesAjax/inhabilitarBases", {bases: $bases}, function(data){
        location.reload();
    });
});

$habilitar.click(function () {
    if(!$noHabiles.val()){
        alert("No ha seleccionado bases para habilitar");
        e.preventDefault();
        return false
    }
    $overlay.show();
    $bases = $noHabiles.val().toString();
    $.post(baseUrl + "/FuncionesAjax/habilitarBases", {bases: $bases}, function(data){
        location.reload();
    });
});