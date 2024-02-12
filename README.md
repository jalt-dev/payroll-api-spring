<h1>Payroll API</h1>
<p>This is the evolution of <a href="https://github.com/jaltdev/payroll-rpc-spring/tree/master">Payroll RPC</a> into a more 
restful application using HAL.</p>

<h3>How to run</h3>
<ol>
    <li>mvn install</li>
    <li>mvn spring-boot:run</li>
    <li><i>note: I've added a postman collection in the postman folder for convenience.</i></li>
</ol>

<h3>Endpoint Paths</h3>

<p>
    <b>GET </b><i>all employees: </i><code>/employees</code><br>
    <b>GET </b><i>one employee: </i><code>/employees/{id}</code><br>
    <b>POST </b><i>new employee: </i><code>/employees</code><br>
    <b>PUT </b><i>edit employee: </i><code>/employees/{id}</code><br>
    <b>DELETE </b><i>one employee: </i><code>/employees/{id}</code><br>
</p>
