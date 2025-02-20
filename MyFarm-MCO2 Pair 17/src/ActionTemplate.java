public abstract class ActionTemplate {
    // Template method that defines the structure of an action
    public final void execute(Tile tile, Seed seed, Player player) {
        if (validate(tile, player)) {
            performAction(tile, seed, player);
            finalizeAction(tile, player);
        } else {
            System.out.println("Action cannot be performed.");
        }
    }

    // Validation before performing the action
    protected abstract boolean validate(Tile tile, Player player);

    // Perform the action (planting in this case)
    protected abstract void performAction(Tile tile, Seed seed, Player player);

    // Final steps after action (optional)
    protected void finalizeAction(Tile tile, Player player) {
        System.out.println("Action completed successfully!");
    }
}