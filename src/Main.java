import com.teamdev.jxcapture.Codec;
import com.teamdev.jxcapture.EncodingParameters;
import com.teamdev.jxcapture.VideoCapture;
import com.teamdev.jxcapture.video.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        VideoCapture videoCapture = VideoCapture.create();
        videoCapture.setVideoSource(new Desktop());

        java.util.List<Codec> videoCodecs = videoCapture.getVideoCodecs();
        Codec videoCodec = videoCodecs.get(0);
        System.out.println("videoCodec = " + videoCodec);

        EncodingParameters encodingParameters =
                new EncodingParameters(
                        new File("Desktop." + videoCapture.getVideoFormat().getId()));
        // Resize output video
        encodingParameters.setSize(new Dimension(1920, 1080));
        encodingParameters.setBitrate(800000);
        encodingParameters.setFramerate(30);
        encodingParameters.setCodec(videoCodec);
        System.out.println("encodingParameters = " + encodingParameters);

        videoCapture.setEncodingParameters(encodingParameters);
        videoCapture.start();

        System.out.println("Recording started. Press 'Enter' to terminate.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        videoCapture.stop();
        System.out.println("Done.");
    }
}
