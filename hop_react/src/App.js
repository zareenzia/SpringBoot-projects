import './App.css';
import Header from './components/Header';
import {ToastContainer, toast} from 'react-toastify';
import { Card,CardBody,CardSubtitle,CardFooter,Container, Button, CardTitle, CardText, Row, Col } from 'reactstrap';
import Menu from './components/Menu';
import Home from './components/Home';
import AddStudent from './components/AddStudents';
import Login from './components/Login';
import AllStudents from './components/AllStudents';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

function App() {
const btnHandle =()=>{
  toast("This is my msg");
}
  return (
    <div className="App">
      <BrowserRouter>
      <ToastContainer/>
      <Container>
        <Header/>
        <Row>
          <Col sm={4}>
            <Menu/>
          </Col>

          <Col sm={8}>
            <Routes>
              <Route path='/add-student' element={<AddStudent />}  />
              <Route path='/viewStudents' element={<AllStudents />} />
              <Route path='/login' element={<Login />} />
              <Route path='/' element={<Home />} />
            </Routes>
          </Col>
        </Row>
      </Container>
      </BrowserRouter>
      
    </div>
  );
}

export default App;
