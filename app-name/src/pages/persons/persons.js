import React, { useEffect, useState } from 'react';
import DataGrid, {
  Column,
  Paging,
  Pager,
  FilterRow,
} from 'devextreme-react/data-grid';

function Person() {
  const [data, setData] = useState([]);

  useEffect(() => {
    // Fetch the data from your JSON endpoint
    fetch('http://localhost:8080/persons')
      .then((response) => response.json())
      .then((data) => {
        setData(data);
        console.log(data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <React.Fragment>
      <h2 className={'content-block'}>Persons</h2>
      <DataGrid
        dataSource={data}
        showBorders={false}
        columnAutoWidth={true}
        columnHidingEnabled={true}
      >
        <Paging defaultPageSize={10} />
        <Pager showPageSizeSelector={true} showInfo={true} />
        <FilterRow visible={true} />
        <Column dataField={'id'} caption={'ID'} width={90} hidingPriority={2} />
        <Column
          dataField={'name'}
          caption={'Name'}
          alignment="left"
          hidingPriority={2}
        />
      </DataGrid>
    </React.Fragment>
  );
}

export default Person;
