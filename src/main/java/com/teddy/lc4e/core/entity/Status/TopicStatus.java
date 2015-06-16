package com.teddy.lc4e.core.entity.Status;

/**
 * Created by teddy on 2015/6/14.
 */
public enum TopicStatus {
    Publish,
    Hide,
    Recycle,
    Deleted,
    Locked,
    UnKonwn;

    public static TopicStatus toStatusEnum(Integer integer) {
        switch (integer) {
            case 1:
                return Publish;
            case 2:
                return Hide;
            case 3:
                return Recycle;
            case 4:
                return Deleted;
            case 5:
                return Locked;
            default:
                return UnKonwn;
        }
    }
}
