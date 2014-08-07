package com.twod.feralvillage;

import java.util.Comparator;

public class ChannelSystem extends BaseObject {
    private static final int CHANNEL_COUNT = 8;
    private static final ChannelComparator sChannelComparator = new ChannelComparator();
    private FixedSizeArray<Channel> mChannels;
    private Channel mSearchDummy;
    private int mRegisteredChannelCount;
    
    public ChannelSystem() {
        super();
        mChannels = new FixedSizeArray<Channel>(CHANNEL_COUNT);
        mChannels.setComparator(sChannelComparator);
        mSearchDummy = new Channel();
        
        for (int x = 0; x < CHANNEL_COUNT; x++) {
            mChannels.add(new Channel());
        }
        
        mRegisteredChannelCount = 0;
    }
    
    @Override
    public void reset() {
        for (int x = 0; x < CHANNEL_COUNT; x++) {
            mChannels.get(x).name = null;
            mChannels.get(x).value = null;
        }
        
        mRegisteredChannelCount = 0;
    }
    
    public Channel registerChannel(String name) {
        Channel result = null;
        mSearchDummy.name = name;
        final int index = mChannels.find(mSearchDummy, false);
        if (index == -1) {
            // Add a new channel.
            assert mRegisteredChannelCount < CHANNEL_COUNT : "Channel pool exhausted!";
            
            if (mRegisteredChannelCount < CHANNEL_COUNT) {
                result = mChannels.get(mRegisteredChannelCount);
                mRegisteredChannelCount++;
                result.name = name;
                mChannels.sort(true);
            }
        } else {
            result = mChannels.get(index);
        }
        
        return result;
    }
    
    public class Channel {
        public String name;
        public Object value;
    }
    
    public static class ChannelFloatValue {
        public float value;
    }
    
    public static class ChannelBooleanValue {
        public boolean value;
    }
    
    /** Comparator for channels. */
    private final static class ChannelComparator implements Comparator<Channel> {
        public int compare(final Channel object1, final Channel object2) {
            int result = 0;
            if (object1 == null && object2 != null) {
                result = 1;
            } else if (object1 != null && object2 == null) {
                result = -1;
            } else if (object1 != null && object2 != null) {
                if (object1.name == null && object2.name != null) {
                    result = 1;
                } else if (object1.name != null && object2.name == null) {
                    result = -1;
                } else if (object1.name != null && object2.name != null) {
                    result = object1.name.compareTo(object2.name);
                }
            }
            return result;
        }
    }

}
