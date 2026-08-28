class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public boolean allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }
        return false;
    }

    public String getSlotNo() { return slotNo; }
    public int getOccupiedCount() { return occupiedCount; }
    public int getCapacity() { return capacity; }

    // Passing the array only copies the REFERENCE to the array, not the ParkingSlot
    // objects it points to — so every element still refers to the same objects in
    // memory, and allot() mutations are visible to the caller after the method returns.
    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot s : slots) {
            if (s.getOccupiedCount() < s.getCapacity()) {
                return s;
            }
        }
        return null;
    }

    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot != null) {
            slot.allot(vehicleNo);
            System.out.println(vehicleNo + " allotted to slot " + slot.getSlotNo());
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] available = { new ParkingSlot("A1", 4, 3), new ParkingSlot("A2", 5, 5) };
        safeAllot(available, "TN09AB1234");

        ParkingSlot[] full = { new ParkingSlot("A1", 4, 4), new ParkingSlot("A2", 5, 5) };
        safeAllot(full, "TN09AB1234");
    }
}