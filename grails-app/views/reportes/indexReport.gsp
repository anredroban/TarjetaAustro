<%@ page import="grails.converters.JSON" contentType="text/html;charset=UTF-8"%>
<html>
<head>
	<asset:stylesheet src="usogeneral/datetimepicker.css" />
	<asset:javascript src="usogeneral/datetimepicker.js" />
	<asset:javascript src="usogeneral/customdatetimepicker.js" />
    <asset:javascript src="reportes/indexReports.js" />
    <asset:javascript src="reportes/datatable.js" />
    %{--<asset:javascript src="jquery.js" />--}%
    <meta name="layout" content="main">
    <title>Reports</title>
</head>

<body>
%{--<g:render template="/layouts/toptabs" model="[tabsContent:['Generar reporte']]" />--}%
	<div class="panel-body">
		<div class="form-horizontal">
			<label for="startDate">Fecha inicio: <input id="startDate" class="startDate" name="startDate" type="text" value="" /></label>
			<label for="endDate"> Fecha final: <input id="stopDate" class="endDate" name="endDate" type="text" value="" /></label>
			<label for="period">
				<g:message code="licenceType.period.label" default="Tipo: " />
				<g:select name="reportsType" from="${["CU1": 'CU1',
													  "CU2": 'CU2',
													  "CU3": 'CU3',
													  "CU4": 'CU4',
													  "CU5": 'CU5',
													  "CU6": 'CU6',
													  "CU7": 'CU7']}"
						  optionKey="key" optionValue="value" noSelection="['':'-Seleccione-']"/>
				<input id="showReport" type="button" class="btn btn-default" value="Ver" />
			</label>
		</label>
		</div>
	<div class="table-responsive">
		<table id="tabladedatos">
			<thead>
			<tr>
				<th>Dia de la llamada</th>
				<th>Numero</th>
				<th>Costo</th>
				<th>Disposicion</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>1</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>2</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>3</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>4</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>5</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>6</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>7</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>8</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>9</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>10</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>11</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>12</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			<tr>
				<td>13</td>
				<td>0968196262</td>
				<td>ANswered</td>
				<td>View</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>