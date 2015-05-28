Q:  How well does your note-searching-api scale or not scale? How would you make your search more efficient?

A:  The note searching api would scale reasonably well for simple one word searches.  For more sophisticated searches, tools
    such as Solr or ElasticSaerch could be considered.  To increase scalability, the notes could be stored in a distributed
    cache solution such as memcached, EHCache or even MOngoDB.

Q.  How would you add security to your API?

A.  REST web services should use session-based authentication, either by establishing a session token via a POST or by using an API key 
    as a POST body argument or as a cookie.  I would add additional security through input validation, strong typing and 
    validation of incoming content types.

Q.  What features should we add to this API next?

A.  I would add date related features such as a deadline and a query for tasks due for a given day.  I would also like 
    to add priorities and classification of tasks.
    
Q.  How would you test the API?

A.  I would use SOAP UI to write test cases using Groovy to validate responses.
