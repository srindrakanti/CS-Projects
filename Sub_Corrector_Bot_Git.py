import praw
import prawcore
bot = praw.Reddit(user_agent='',                                                  #Account information hidden. Sorry!
                  client_id='',
                  client_secret='',
                  username='Sub_Corrector_Bot',
                  password='')
template="\n \n ---------------------------------------" + "\n \n^^^Remember,&#32;OP&#32;may&#32;have&#32;ninja-edited.&#32;I&#32;correct&#32;subreddit&#32;and&#32;user&#32;links&#32;with&#32;a&#32;capital&#32;R&#32;or&#32;U,&#32;which&#32;are&#32;usually&#32;unusable. \n \n ^^**-Srikar**"
#^This is the bot's signature under each comment
subreddit = bot.subreddit('all')
comments = subreddit.stream.comments()
logs=[]
while True:
    try:
        for comment in comments:                                                  # For every comment that the code reads
            author=comment.author.name
            if (author != "Sub_Corrector_Bot") and (comment.id not in logs) :     # Doesn't reply to self
                text = comment.body                                               # The text in the comment
                words=text.split()                                                # Seperates the comment into a list of words
                for i in words:                                                   # Searches each word in list for incorrect link
                    if ("R/" in i) and (text!=text.upper()):
                        if (not("R/T" in i)and not("hockey" in i) and not("3DS" in i) and not("CFB" in i)): #Certain subreddits won't ban the bot but hate it, so I blacklist them here.
                            if (not text.count('/')>1):
                                index=i.index("R")                                # Start:
                                le=len(i)
                                temp=list(i)
                                if (index==1 and le<=24 and le>=5 and temp[0]=="/"and temp[1]=="R" and temp[2]=="/") or (index==0 and le<=23 and le>=4 and temp[0]=="R" and temp[1]=="/"):         
                                    temp[index]="r"
                                    answer="".join(temp)                          # End: Turns capital R into lowercase r
                                    sentence="You may have meant "+ answer+ " instead of " + i +"."
                                    comment.reply(sentence + template)
                                    logs.append(comment.id)
                    elif "U/" in i:
                        indexu=i.index("U")                                       # Start:
                        leu=len(i)
                        tempu=list(i)
                        if (indexu==1 and leu<=23 and leu>=6 and tempu[0]=="/"and tempu[1]=="U" and tempu[2]=="/") or (indexu==0 and leu<=22 and leu>=5 and tempu[0]=="U" and tempu[1]=="/"):         
                            tempu[indexu]="u"
                            answeru="".join(tempu)                                # End: Turns capital U into lowercase u
                            sentenceu="You may have meant "+ answeru+ " instead of " + i +"."
                            comment.reply(sentenceu + template)
                            logs.append(comment.id)
    except (prawcore.exceptions.Forbidden,Exception):
        pass
