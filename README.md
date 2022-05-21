# Discord Jemba by *Chris Penno*

#### In Progress Project

### Brief:

My flat mates and I created a version of the traditional [Jenga](https://en.wikipedia.org/wiki/Jenga) game in which single word rules were
written onto the wooden blocks. As you pull a block you must perform the action linked to the rule on the block; anything from run around the 
flat once to pull an entire layer on your next turn. Eventually, we grew bored of the same 54 blocks, so bought another set and combined the 
two, layering five pieces on the small edge per layer. This tower, a combination of two jenga sets, we dubbed "Jemba". 

After two years or so, the printed rule sheets we used to match the rules on the blocks with the action to perform were crinckled and wearing.
I decided it would be easier to integrate the rules into a Discord Bot, using slash commands to return the rule in a channel of our flat discord
server. This will be much easier than printing another two sheets of paper, and save the trees too!

### State: 

This project is something I am doing in my free time, something that my final year at University is hampering. None the less, progress has been
made on this project.

###### Completed
- [x] Generate a live bot that is activated by a Java application
- [x] Add commands to the bot's slash library
- [x] Handle the slash command to the relevant function 
- [x] Dummy function to practice CSV processing and returning

###### Ongoing
- [ ] Experimenting with embeded messages and buttons

###### To Do
- [ ] Functionality for local CSV reading as rules
- [ ] Auto-populate commands from the rules
- [ ] Streamline application for ease of use and creation server-side
- [ ] Update README with set up instructions for the curious 

---
### Future Features:

While the basic functionality has not yet been completed to my satisfaction, I have had suggestions for feature creep from my peers and other 
coders. I cannot promise all will be implemented, however, I do wish to see some implemeneted. So watch this space!

- Look into slash commands vs. text inputs
- Repurpose for general rules
  - The game of Jemba is very specific and I doubt many households have two Jenga sets, let alone have the need for a Discord Bot to act as a 
companion while playing. One way to make this software match a broader range of applications is to implement functionailtity to allow users to 
upload their own csv file of rules, which will then be autofill as slash commands for the bot
- Create more interactivity with the bot to track the players and actions in the game. Would generate data from across multiple play sessions
