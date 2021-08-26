<!-- Modal -->
<div id="modalReferido" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Agregar Referido</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <g:textField placeholder="Cédula" class="form-control" name="referidoCedula"></g:textField>
                </div>
                <div class="form-group">
                    <g:textField placeholder="Nombres" class="form-control" name="referidoNombres"></g:textField>
                </div>
                <div class="form-group">
                    <g:textField placeholder="Apellidos" class="form-control" name="referidoApellidos"></g:textField>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="btnGuardarReferido" type="button" class="btn btn-success">Guardar</button>
            </div>
        </div>

    </div>
</div>