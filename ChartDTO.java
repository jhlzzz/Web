package soo.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartDTO {
	private String item;
	private int number;
	
	@Override
	public String toString() {
		return "#GoogleChartDTO item: " + item + ", value: " + number;
	}
	
}
