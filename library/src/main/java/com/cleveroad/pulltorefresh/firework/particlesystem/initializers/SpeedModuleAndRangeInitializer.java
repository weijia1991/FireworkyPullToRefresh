package com.cleveroad.pulltorefresh.firework.particlesystem.initializers;

import com.cleveroad.pulltorefresh.firework.particlesystem.Particle;

import java.util.Random;


public class SpeedModuleAndRangeInitializer implements ParticleInitializer {

	private float mSpeedMin;
	private float mSpeedMax;
	private int mMinAngle;
	private int mMaxAngle;

	public SpeedModuleAndRangeInitializer(float speedMin, float speedMax, int minAngle, int maxAngle) {
		mSpeedMin = speedMin;
		mSpeedMax = speedMax;
		mMinAngle = minAngle;
		mMaxAngle = maxAngle;
		// Make sure the angles are in the [0-360) range
		while (mMinAngle < 0) {
			mMinAngle+=360;
		}
		while (mMaxAngle < 0) {
			mMaxAngle+=360;
		}
		// Also make sure that mMinAngle is the smaller
		if (mMinAngle > mMaxAngle) {
			int tmp = mMinAngle;
			mMinAngle = mMaxAngle;
			mMaxAngle = tmp;
		}
	}

	@Override
	public void initParticle(Particle particle, Random random) {
		float speed = random.nextFloat()*(mSpeedMax-mSpeedMin) + mSpeedMin;
		int angle;
		if (mMaxAngle == mMinAngle) {
			angle = mMinAngle;
		}
		else {
			angle = random.nextInt(mMaxAngle - mMinAngle) + mMinAngle;
		}
		float angleInRads = (float) (angle*Math.PI/180f);
		particle.setSpeedX((float) (speed * Math.cos(angleInRads)));
		particle.setSpeedY((float) (speed * Math.sin(angleInRads)));
	}

}
