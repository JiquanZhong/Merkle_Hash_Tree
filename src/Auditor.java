import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class Auditor {
    Hash rootHash;
    LogServer server;

    public Auditor(LogServer server) {
        rootHash = server.tree.hash;
        this.server = server;
    }

    public boolean isMember(String event, int index) {
        LinkedList<Hash> path = server.genPath(index);
        Hash hash = buildHash(event, index, server.tree.end, path);
        return hash.equals(rootHash);
    }

    public Hash buildHash(String event, int index, int end, LinkedList<Hash> path) {
        System.out.println("Ends at: " + end);
        if (end == 0) {
            if (path.size() != 0) {
                System.out.println("hash list not empty!");
            }
            return new Hash(event);
        }
        int middle = greatestPowerTwoSmaller(end);

        if (index < middle) {
            Hash right = path.removeLast();
            Hash left = buildHash(event, index, middle - 1, path);
            System.out.println("Receiving hash:" + left.toString());
            System.out.println("Merging with right:" + right.toString());
            return new Hash(left, right);
        }
        Hash left = path.removeLast();
        Hash right = buildHash(event, index - middle, end - middle, path);
        System.out.println("Receiving hash:" + right.toString());
        System.out.println("Merging with left:" + left.toString());
        return new Hash(left, right);
    }


    public boolean isConsistent(LogServer newLogServer) {
        if (server.tree.size > newLogServer.tree.size)
            return false;
        if (server.tree.size == newLogServer.tree.size) {
            if (rootHash.equals(newLogServer.currentRootHash())) {
                return true;
            }
            return false;
        }

        int index = server.tree.end;
        LinkedList<Hash> path = newLogServer.genProof(index);

        int end = newLogServer.tree.end;

        while (index % 2 != 0) {
            end /= 2;
            index /= 2;
        }//index is now even
        Hash hash = path.removeFirst();
        hash = buildProofHash(hash, index, end, path);

        System.out.println(hash.toString());
        return hash.equals(rootHash);
    }

    public Hash buildProofHash(Hash hash, int index, int end, LinkedList<Hash> path) {
        System.out.println("Ends at: " + end);

        if (end == 0) {
            if (path.size() != 0) {
                System.out.println("hash list not empty!");
            }
            return hash;
        }
        int middle = greatestPowerTwoSmaller(end);

        if (index < middle) {
            Hash removed = path.removeLast();//It's on the right, we don't use it
            System.out.println("Removing hash:" + removed.toString());
            return buildProofHash(hash, index, middle - 1, path);
        }
        Hash left = path.removeLast();
        Hash right = buildProofHash(hash, index - middle, end - middle, path);
        System.out.println("Receiving hash:" + right.toString());
        System.out.println("Merging with left:" + left.toString());
        return new Hash(left, right);
    }

    public int greatestPowerTwoSmaller(int index) {
        return Integer.highestOneBit(index);
    }

}
