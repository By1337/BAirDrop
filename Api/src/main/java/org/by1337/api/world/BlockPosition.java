package org.by1337.api.world;

public class BlockPosition {
    public int x = 0;
    public int y = 0;
    public int z = 0;

    public BlockPosition() {
    }

    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPosition add(BlockPosition position) {
        return new BlockPosition(x + position.x, y + position.y, z + position.z);
    }
    public BlockPosition add(int x, int y, int z) {
        return new BlockPosition(this.x + x, this.y + y, this.z + z);
    }

    public BlockPosition subtract(BlockPosition position) {
        return new BlockPosition(x - position.x, y - position.y, z - position.z);
    }
    public BlockPosition subtract(int x, int y, int z) {
        return new BlockPosition(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public String toString() {
        return "BlockPosition{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
