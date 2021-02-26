package com.intive.patronage.calc.converters;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class VectorConverterTest {

	VectorConverter vectorConverter = new VectorConverter();

	BigDecimal[] vector = new BigDecimal[] { new BigDecimal(1), new BigDecimal(2), new BigDecimal(3) };

	@Test
	public void should_convert_matrix_to_String() {
		//given

		//when
		String entity = this.vectorConverter.convertToDatabaseColumn(vector);
		//then
		assertEquals("1;2;3", entity);

	}

	@Test
	public void should_convert_String_to_BigDecimal_two_dimensional_array() {
		//given

		String dbEntity = "1;2;3";
		//when
		BigDecimal[] vectorFromDb = vectorConverter.convertToEntityAttribute(dbEntity);
		//then
		assertArrayEquals(vector, vectorFromDb);
	}

}
