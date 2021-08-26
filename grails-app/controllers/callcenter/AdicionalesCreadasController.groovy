package callcenter

import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import liquibase.util.file.FilenameUtils

class AdicionalesCreadasController {

    def cargarBase(){

    }

    def saveFile(){
        String[] formFields = AdicionalesCreadas.getFields()
        def file = request.getFile('file')
        Cell[] cells
        String[] headers
        if(file.empty){
            flash.message = "Por favor selecciona un archivo"
        }else{
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder) //app directory
            File fileDest = new File(webrootDir,file.getOriginalFilename())
            if(fileDest.mkdirs()){
                println "directory created"
            }else{
                println "directory not created or already exists"
            }
            file.transferTo(fileDest)

            //Reading Excel
            String ext = FilenameUtils.getExtension(fileDest.path)
            if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx")){
                try {
                    WorkbookSettings ws = new WorkbookSettings()
                    ws.setEncoding("Cp1252")
                    Workbook workbook = Workbook.getWorkbook(fileDest, ws)
                    Sheet sheet = workbook.getSheet(0)
                    cells = sheet.getRow(0)
                    workbook.close()
                }catch (IOException ex){
                    flash.error = "Problemas al cargar el archivo"
                    render(view: "cargarBase")
                }
                headers = new String[cells.length]
                for(int i = 0; i < cells.length; i++){
                    headers[i] = cells[i].getContents()
                }
                render(view: "sortExcel", model: [headers: headers, formFields:formFields, filePath:fileDest.path])
            }else{
                flash.error = "El archivo debe ser una hoja de cálculo de Excel"
                render(view: "cargarBase")
            }
        }
    }

}
