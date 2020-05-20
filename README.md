May 2, 2020:
Following https://www.youtube.com/watch?v=24SsQp-UBFI tutorial (part 4 or potential 8 or so) for creation of login.

May 4, 2020:
Following https://www.youtube.com/watch?v=fGcMLu1GJEc&vl=en tutorial (3 part series) for creation of the navigation bar from scratch, 
hiding the default actionbar. This tutorial is good because it uses fragments (see below).

Found out today that multi-activity apps are not the best for scaling, and Google recommends developping using the single activity 
architecture (framgent-based). Will look further into that later.

May 17, 2020:
Completed tutorials https://www.youtube.com/watch?v=bjYstsO1PgI&list=PLrnPJCHvNZuDQ-jWPw13-wY2J57Z6ep6G&index=3
and https://www.youtube.com/watch?v=7hUN5gLx1L0 to have functional navigation menu with basic fragments.

May 19, 2020:
The login page and registration page don't even come up, as the app defualts to the NewsRoom right away. Gotta find
where that happens, and add a logout button which will change activities, back to the login page.
->In mainActivity.java, i set the default. But when that part is removed, the app still begin in mainActivity, with
the hamburger symbol to the left, and an empty page.
I guess what I did was open an activity from an activity. Now going to check to open activity from fragment
https://www.google.com/search?q=how+to+open+activity+from+fragment+on+button+click+in+android&oq=how+to+open+activity+from+fra&aqs=chrome.0.0j69i57j0l6.8550j0j7&sourceid=chrome&ie=UTF-8