function customGenerator() {
	var title = '<div class="selector" id="custom_selector"><h3>Select options to create a graph:</h3>';
	var graphOptions = [
		title,
		'<table class="selector-table">',
			'<tr></tr>',
			'<tr>',
			'<th>start date:</th>',
				'<td><input type="date" id="startDate"></td>',
			'</tr>',
			'<tr>',
				'<th>start time:</th>',
				'<td><input type="time" id="startName"></td>',
			'</tr>',
			'<tr>',
				'<th>end date:</th>',
				'<td><input type="date" id="endDate"></td>',
			'</tr>',
			'<tr>',
				'<th>end time:</th>',
				'<td><input type="time" id="endTime"></td>',
				'</tr><tr>',
				'<th colspan="3"></th>',
			'</tr>',
		'</table>',
		'</div>',
		'<div class="selector" id="selector-graph">',
			'<p><b>Select a graph:</b></p>',
			'<select id="selector-select-graph">',
				'<option value="temperature" id="selector-option-temp">Temperature</option>',
				'<option value="rain" id="selector-option-rain">Rainfall</option>',
				'<option value="snowfall" id="selector-option-snowfall">Snowfall</option>',
			'</select>',
			'<select id="selector-select-graph-time">',
				'<option value="hour" id="selector-option-hour">Hour</option>',
				'<option value="minute" id="selector-option-minute">Minute</option>',
				'<option value="second" id="selector-option-second">Second</option>',
			'</select>',
		'</div>'
	].join("\n");
	
	return graphOptions;

}