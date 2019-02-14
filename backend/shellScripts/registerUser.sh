echo $( curl -H "Content-Type: application/json" -X POST -d '{
    "username": "admin",
    "password": "password",
    "email": "admin@frently.com",
    "bankAccount": "admin.mcAdminFace"
}' http://localhost:8080/users/sign-up ) | cat
read  -n 1 -p "" mainmenuinput