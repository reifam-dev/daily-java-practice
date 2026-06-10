public class Day61EnumModule {

    enum Direction {
        NORTH, SOUTH, EAST, WEST;

        public Direction opposite() {
            switch (this) {
                case NORTH: return SOUTH;
                case SOUTH: return NORTH;
                case EAST:  return WEST;
                case WEST:  return EAST;
                default:    return this;
            }
        }
    }

    enum Status {
        PENDING("pending"),
        ACTIVE("active"),
        CLOSED("closed"),
        REJECTED("rejected");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() { return value; }

        public boolean isTerminal() {
            return this == CLOSED || this == REJECTED;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Direction enum ===\n");
        Direction d = Direction.NORTH;
        System.out.println("  Direction  : " + d);
        System.out.println("  Name       : " + d.name());
        System.out.println("  Ordinal    : " + d.ordinal());
        System.out.println("  Opposite   : " + d.opposite());

        System.out.println("\n  All directions:");
        for (Direction dir : Direction.values()) {
            System.out.println("    " + dir.name() + " = " + dir.ordinal());
        }

        System.out.println("\n=== Status enum ===\n");
        Status s = Status.ACTIVE;
        System.out.println("  Status     : " + s);
        System.out.println("  Value      : " + s.getValue());
        System.out.println("  Terminal   : " + s.isTerminal());
        System.out.println("  Closed terminal: " + Status.CLOSED.isTerminal());

        System.out.println("\n=== Enum comparison ===\n");
        System.out.println("  NORTH == NORTH : " + (Direction.NORTH == Direction.NORTH));
        System.out.println("  equals         : " + Direction.NORTH.equals(Direction.NORTH));

    }

}