package com.se.dto;

import lombok.Data;

@Data
public class SelectedRoomListDto {
	String selectedRoomList;

	@Override
	public String toString() {
		return selectedRoomList.replace(",", ", Room");
	}
}
