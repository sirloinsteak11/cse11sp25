public class Testing {
    public static void main(String[] args) {
        PassengerPlane pp1 = new PassengerPlane("HE11", "boeing 777",
                                                1000, 15, 4, "Yuh engine",
                                                "Rob Lock Airlines", 
                                                "RLA7003", 165);

        System.out.println(pp1.toString());
    }
}
