from midiutil.MidiFile3 import MIDIFile
MyMIDI = MIDIFile(1)
track = 0
time = 0
MyMIDI.addTrackName(track,time,"Sample Track")
MyMIDI.addTempo(track,time,120)
track = 0
channel = 0
pitch = 60
time = 0
duration = 1
volume = 100

chords = {}



MyMIDI.addNote(track,channel,50,time,duration,volume)
MyMIDI.addNote(track,channel,70,2,duration,volume)
MyMIDI.addNote(track,channel,80,3,duration,volume)
binfile = open("output.mid", 'wb')
MyMIDI.writeFile(binfile)
binfile.close()