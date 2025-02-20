public abstract class ActionTemplate {
    public final void execute(Tile tile, Seed seed, Player player) {
        if (validate(tile, seed, player)) {
            performAction(tile, seed, player);
            finalizeAction(tile, player);
        } else {
            System.out.println("Action cannot be performed.");
        }
    }

    protected abstract boolean validate(Tile tile, Seed seed, Player player);

    protected abstract void performAction(Tile tile, Seed seed, Player player);

    protected void finalizeAction(Tile tile, Player player) {
        System.out.println("Action completed successfully!");
    }
}
