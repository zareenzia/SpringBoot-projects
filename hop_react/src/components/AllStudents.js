import React, { useEffect, useState } from "react";
import { Card,CardBody,CardSubtitle,CardFooter,Container, Button, CardTitle, CardText, Row, Col } from 'reactstrap';
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AllStudents() {
  const [students, setStudents] = useState([]);
  const [isError, setError] = useState(false);

  useEffect(()=>{
    getStudentList();
   },[]) 

   const getStudentList=()=>{
    try {
      const token = localStorage.getItem("access_token");
      if(token){
        let axiosConfig = {
          headers: {            
              "Access-Control-Allow-Origin": "*",
              "Authorization": `Bearer ${token}`,
          }
        };
        axios.get('http://localhost:3000/controller/students',axiosConfig)
        .then((response) =>{
          console.log(response)
          setStudents(response?.data);
        })
        .catch((err)=>{
            setError(true)
        })

      }else{
        
      }
      
    } catch (error) {
      
    }
   }
 
   return (
    <div>
      <h1>All Student Information</h1>
      {
      students?.map((student) => {
          return (      
            <div key={student.id}>
                <Row>
                  <Col>
                    <Card >
                      <CardBody>
                          <CardTitle>Student ID: {student.id}</CardTitle>                
                          <CardText>Name:{student.name} </CardText>
                          <CardText>Address: {student.address}</CardText>
                          <CardText>Age:{student.age} </CardText>
                          <Button style={{marginRight: "10px"}}>Update</Button>
                          <Button color='danger'>Delete</Button>
                      </CardBody>
                    </Card>
                  </Col>
                </Row>
                  
          </div>
          )
        })
      }
    </div>
  )
}
        