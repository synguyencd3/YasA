import Dropdown from 'react-bootstrap/Dropdown';

const SortDropdown = ({setSort, setSortBy}) => {
    return (
        <div>
            <Dropdown className="mt-3">
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort By
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    <Dropdown.Item href="#/default" onClick={() =>setSortBy("id")}>Default</Dropdown.Item>
                    <Dropdown.Item href="#/name" onClick={() =>setSortBy("name")}>Name</Dropdown.Item>
                </Dropdown.Menu>
                </Dropdown>

                <Dropdown className="mt-3">
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item href="#/asc" onClick={() =>setSort("asc")}>Ascending</Dropdown.Item>
                    <Dropdown.Item href="#/desc" onClick={() =>setSort("desc")}>Descending</Dropdown.Item>
                </Dropdown.Menu>
                </Dropdown>
            </div>
    )
}

export default SortDropdown;