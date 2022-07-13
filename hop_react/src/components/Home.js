import React from "react";
import { Container} from 'reactstrap';
import {Card, CardBody} from "reactstrap";

const Home=()=>{
    return(
        <div>
            <Card style={{backgroundColor: "#EAF4F4"}}>
                <h3>Home page</h3>
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of 
                    type and scrambled it to make a type specimen book. 
                    </p>
            </Card>
        </div>
    )
}

export default Home;