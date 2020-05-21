import React from "react";
import { Select as SelectAnt } from "antd";
import PropTypes from "prop-types";
import "./select.scss";

export default function Select({ style, options, placeholder, onChange }) {
  return (
    <SelectAnt
      showSearch
      style={style}
      placeholder={placeholder}
      optionFilterProp="children"
      onChange={onChange}
      filterOption={(input, option) =>
        option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
      }
    >
      {options}
    </SelectAnt>
  );
}
Select.propTypes = {
  className: PropTypes.string,
  options: PropTypes.array.isRequired,
  placeholder: PropTypes.string,
  onSelect: PropTypes.func,
};
