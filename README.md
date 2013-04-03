HOW TO : get the last modifications pushed on the remote repository

    1 - Pull everything from the remote repository (the github one)
    2 - Reset hard with your local master
    3 - Switch to your local branch
    4 - Rebase your branch with your local master (sometime, you have some rebasing conflicts but don't worry about that too much)
     
HOW TO : work with your branch

    One simple rule : NEVER work in the local master branch

 

    1 - Switch to your branch (the local one)
    2 - Code and commit as much as you want
    3 - a) you can push your code to your remote branch (push upstream)
       - b) you can merge your code with the local master
                -> FIRST : get the last modifications done in the remote local (first HOW TO)

               -> switch to the master (local)
               -> merge with your branch
               -> Push to the remote repository

