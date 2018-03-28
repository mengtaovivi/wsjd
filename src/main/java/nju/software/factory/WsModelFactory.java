package nju.software.factory;

import nju.software.wsjx.facade.impl.WsModelFacadeImpl;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.InputStream;

/**
 * Created by away on 2018/3/28.
 */
// ͬһ������ֻ����һƪ
public class WsModelFactory {

    private static volatile String name;
    private static volatile WsModel wsModel;
    private WsModelFactory() {}

    public static WsModel getInstance(InputStream is, String filename) {
        // ����ı���һ�γ�ʼ������ʱ����
        if (!filename.equals(name) || wsModel == null) {
            synchronized (WsModel.class) {
                if (!filename.equals(name) || wsModel == null) {
                    WsModelFacadeImpl wsModelFacadeImpl = new WsModelFacadeImpl();
                    wsModel = wsModelFacadeImpl.jxDocument(is, filename);
                    name = filename;
                }
            }
        }
        return wsModel;
    }
}
