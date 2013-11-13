package game.util;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SoundBank
{
	public static Audio fireSound = getFireSound(), endSound = getEndSound();
	public static Audio clickSound = getClickSound(), chrunchSound = getChrunchSound();
	public static Audio thudSound = getThudSound(), sizzleSound = getSizzleSound();
	public static Audio flameSound = getFlameSound(), stormSound = getStormSound();
	public static Audio chimeSound = getChimeSound();
	private static Audio getFireSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/boom.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getEndSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/end_sound.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getClickSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/click.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getChrunchSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/chrunch.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getThudSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/thud.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getSizzleSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sizzle.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getFlameSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/flame.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getStormSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/storm.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
	private static Audio getChimeSound() 
	{
		Audio newsfx = null;
	    try 
	    {
	        newsfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/chime.wav"));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return newsfx;
	}
}
