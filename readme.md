# Script Generator

I often write scripts for video training, youtube, etc.

I like to write scripts in a linear format because it is easier to read and review e.g.

~~~~~~~~
Notes:

Some stuff about the script

Some more info

---

DO:

- list of actions to do
- do this other thing

```
some code to type
```

SAY:

And this is what I would be saying.

As I did the actions above
~~~~~~~~

While that format is useful to write, it is less useful to read, I prefer a two column format for recording from.

| SAY THIS | DO THIS |
|----|----|
| "hello" | open browser |

So I wrote this code to take a folder of 'scripts' in a markdown format as shown above.

And output to a folder the 'html' that I can work from when recording.

This is very basic at the moment.

If you want to use it then amend the @Test method, add the folders, then run the Test method.

Oh, and it also outputs a word count and estimated time for each script based on the number of words in the `SAY:` sections. 

