package SEproject.hello.common.model;

import javax.swing.filechooser.FileSystemView;

public class BaseUrl {

    public static String test_BASE_URL = FileSystemView.getFileSystemView().getHomeDirectory().toString() + "/" + "mbtiTestImg/";

}
