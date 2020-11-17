class Tea {
    String type;
    int steepTime;

    @Override
    boolean equals(Object o) {
        if (!(o instanceof Tea)) return false;
        Tea ot = (Tea) o;
        return ot.type.equals(this.type)
                && ot.steepTime == this.steepTime;
    }

    @Override
    int hashCode() {
        return random(); // returns a random integer
    }

    public static void main(String[] args) {
        Map<Tea, Integer> teaRatings = new HashMap<Tea, Integer>();
        teaRatings.put(new Tea("Earl Grey", 3), 44);
        teaRatings.put(new Tea("Jasmine", 1), 52);
        teaRatings.put(new Tea("Oolong", 3), 72);
        System.out.println(teaRatings.get(new Tea("Earl Grey", 3)));
    }
}