package cursach;

import java.util.Arrays;

public class BinaryContent {
    private final byte[] content;

    public BinaryContent(byte[] content) {

        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "BinaryContent{" +
//                "content=" + Arrays.toString(content) +
                "size_bait=  " + content.length +
                '}';
    }
}
