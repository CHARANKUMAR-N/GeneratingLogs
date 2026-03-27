package com.logs.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final ElasticService elasticService;

	public OrderService(ElasticService elasticService) {
		this.elasticService = elasticService;
	}

	public void generateError() {

		// 1️⃣ NullPointerException
		try {
			String order = null;
			order.length();
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "NullPointerException occurred", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 2️⃣ ArrayIndexOutOfBoundsException
		try {
			int[] arr = new int[2];
			int n = arr[10];
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "ArrayIndexOutOfBoundsException occurred", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 3️⃣ ArithmeticException
		try {
			int x = 5 / 0;
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "ArithmeticException occurred", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 4️⃣ NumberFormatException
		try {
			int a = Integer.parseInt("XYZ");
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "NumberFormatException occurred", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 5️⃣ IllegalArgumentException
		try {
			Integer.valueOf(null);
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "IllegalArgumentException occurred", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		try {
			String str = "ABC";
			char ch = str.charAt(10);
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "StringIndexOutOfBoundsException occurred", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void generateMoreErrors() {

		// 🔹 ArrayIndexOutOfBoundsException
		try {
			int[] numbers = { 1, 2, 3 };
			int value = numbers[5]; // invalid index
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR",
						"ArrayIndexOutOfBoundsException occurred in generateMoreErrors()", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// 🔹 StringIndexOutOfBoundsException
		try {
			String text = "Hello";
			char c = text.charAt(20); // invalid index
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR",
						"StringIndexOutOfBoundsException occurred in generateMoreErrors()", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		try {
			throw new Exception("This is a manually thrown exception for testing");
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "Manually triggered exception in generateMoreErrors()",
						e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		try {
			throw new RuntimeException("Business rule failed manually");
		} catch (Exception e) {
			try {
				elasticService.saveLog("order-service", "ERROR", "Manual business rule exception", e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}