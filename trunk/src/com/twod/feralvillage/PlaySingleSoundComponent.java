package com.twod.feralvillage;

public class PlaySingleSoundComponent extends GameComponent {
	private SoundSystem.Sound mSound;
	private int mSoundHandle;
	
	public PlaySingleSoundComponent() {
		super();
		reset();
        setPhase(ComponentPhases.THINK.ordinal());
	}
	
	@Override
	public void reset() {
		mSoundHandle = -1;
		mSound = null;
	}
	
	public void setSound(SoundSystem.Sound sound) {
		mSound = sound;
	}
	
	@Override
    public void update(float timeDelta, BaseObject parent) {
		if (mSoundHandle == -1 && mSound != null) {
			SoundSystem sound = sSystemRegistry.soundSystem;
			mSoundHandle = sound.play(mSound, false, SoundSystem.PRIORITY_NORMAL);
		}
	}
}