import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";


const NavigationBar = () => {
    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand href="/">Home</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <NavDropdown title="City" id="basic-nav-dropdown">
                    <NavDropdown.Item href="/city/create">Create City</NavDropdown.Item>
                    <NavDropdown.Item href="/city/get">Get City</NavDropdown.Item>
                    <NavDropdown.Item href="/city/update">Update City</NavDropdown.Item>
                    <NavDropdown.Item href="/city/all">Update City</NavDropdown.Item>
                    <NavDropdown.Divider />
                    <NavDropdown.Item href="/city/delete">Delete City</NavDropdown.Item>
                </NavDropdown>
            </Container>
        </Navbar>
    )
}

export default NavigationBar;
