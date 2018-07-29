package org.example.test.convert;

public class Paso2 implements IConvert {
	public static final char[] MAP = {
		(char) 256-128, (char) 256-127, (char) 256-126, (char) 256-125, (char) 256-124, (char) 256-123, (char) 256-122, (char) 256-121, (char) 256-120,
		(char) 256-119, (char) 256-118, (char) 256-117, (char) 256-116, (char) 256-115, (char) 256-114, (char) 256-113, (char) 256-112, (char) 256-111, (char) 256-110,
		(char) 256-109, (char) 256-108, (char) 256-107, (char) 256-106, (char) 256-105, (char) 256-104, (char) 256-103, (char) 256-102, (char) 256-101, (char) 256-100,
		(char) 256-99, (char) 256-98, (char) 256-97, (char) 256-96, (char) 256-95, (char) 256-94, (char) 256-93, (char) 256-92, (char) 256-91, (char) 256-90,
		(char) 256-89, (char) 256-88, (char) 256-87, (char) 256-86, (char) 256-85, (char) 256-84, (char) 256-83, (char) 256-82, (char) 256-81, (char) 256-80,
		(char) 256-79, (char) 256-78, (char) 256-77, (char) 256-76, (char) 256-75, (char) 256-74, (char) 256-73, (char) 256-72, (char) 256-71, (char) 256-70,
		(char) 256-69, (char) 256-68, (char) 256-67, (char) 256-66, (char) 256-65, (char) 256-64, (char) 256-63, (char) 256-62, (char) 256-61, (char) 256-60,
		(char) 256-59, (char) 256-58, (char) 256-57, (char) 256-56, (char) 256-55, (char) 256-54, (char) 256-53, (char) 256-52, (char) 256-51, (char) 256-50,
		(char) 256-49, (char) 256-48, (char) 256-47, (char) 256-46, (char) 256-45, (char) 256-44, (char) 256-43, (char) 256-42, (char) 256-41, (char) 256-40,
		(char) 256-39, (char) 256-38, (char) 256-37, (char) 256-36, (char) 256-35, (char) 256-34, (char) 256-33, (char) 256-32, (char) 256-31, (char) 256-30,
		(char) 256-29, (char) 256-28, (char) 256-27, (char) 256-26, (char) 256-25, (char) 256-24, (char) 256-23, (char) 256-22, (char) 256-21, (char) 256-20,
		(char) 256-19, (char) 256-18, (char) 256-17, (char) 256-16, (char) 256-15, (char) 256-14, (char) 256-13, (char) 256-12, (char) 256-11, (char) 256-10,
		(char) 256-9, (char) 256-8, (char) 256-7, (char) 256-6, (char) 256-5, (char) 256-4, (char) 256-3, (char) 256-2, (char) 256-1,
		(char) 0, (char) 1, (char) 2, (char) 3, (char) 4, (char) 5, (char) 6, (char) 7, (char) 8, (char) 9,
		(char) 10, (char) 11, (char) 12, (char) 13, (char) 14, (char) 15, (char) 16, (char) 17, (char) 18, (char) 19,
		(char) 20, (char) 21, (char) 22, (char) 23, (char) 24, (char) 25, (char) 26, (char) 27, (char) 28, (char) 29,
		(char) 30, (char) 31, (char) 32, (char) 33, (char) 34, (char) 35, (char) 36, (char) 37, (char) 38, (char) 39,
		(char) 40, (char) 41, (char) 42, (char) 43, (char) 44, (char) 45, (char) 46, (char) 47, (char) 48, (char) 49,
		(char) 50, (char) 51, (char) 52, (char) 53, (char) 54, (char) 55, (char) 56, (char) 57, (char) 58, (char) 59,
		(char) 60, (char) 61, (char) 62, (char) 63, (char) 64, (char) 65, (char) 66, (char) 67, (char) 68, (char) 69,
		(char) 70, (char) 71, (char) 72, (char) 73, (char) 74, (char) 75, (char) 76, (char) 77, (char) 78, (char) 79,
		(char) 80, (char) 81, (char) 82, (char) 83, (char) 84, (char) 85, (char) 86, (char) 87, (char) 88, (char) 89,
		(char) 90, (char) 91, (char) 92, (char) 93, (char) 94, (char) 95, (char) 96, (char) 97, (char) 98, (char) 99,
		(char) 100, (char) 101, (char) 102, (char) 103, (char) 104, (char) 105, (char) 106, (char) 107, (char) 108, (char) 109,
		(char) 110, (char) 111, (char) 112, (char) 113, (char) 114, (char) 115, (char) 116, (char) 117, (char) 118, (char) 119,
		(char) 120, (char) 121, (char) 122, (char) 123, (char) 124, (char) 125, (char) 126, (char) 127
	};
	
	public int convert(
			byte input[], int byteStart, int byteEnd, char output[],
			int charStart, int charEnd) throws Exception {
		int charOff = charStart;
		for (int byteOff = byteStart; byteOff < byteEnd;) {
			if (charOff >= charEnd) throw new Exception();
			int i1 = input[byteOff++];
			output[charOff++] = MAP[128 + i1];
		}
		return charOff - charStart;
	}
}
