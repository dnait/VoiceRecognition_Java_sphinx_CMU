//  Created by Rao Xu
//  Copyright (c) 2014. All rights reserved.
package sphinxTTScalculator


import com.sun.speech.freetts.*;

public class ttsspeech  {

    public void speak(String mystring){       
    final String VOICENAME="kevin16";
                                    
        Voice voice;
        VoiceManager vm=VoiceManager.getInstance();
        voice=vm.getVoice(VOICENAME);
        voice.allocate();
        voice.speak(mystring);
    }
    }



