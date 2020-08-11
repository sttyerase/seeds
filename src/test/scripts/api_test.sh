
## VALIDATE ROOT PAGE
curl http://localhost:8080/seedinspection/
if [ $? -ne 0 ] ; then echo "FAILURE at home page."; else echo "SUCCESS" ; fi
## VALIDATE GET ALL
curl http://localhost:8080/seedinspection/crops/all
if [ $? -ne 0 ] ; then echo "FAILURE at home page."; else echo "SUCCESS" ; fi
