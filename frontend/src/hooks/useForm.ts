import { ChangeEvent, useState } from "react";

export const useForm = <T>(defaultForm: T) => {
  const [form, setForm] = useState<T>(defaultForm);
  const onChangeForm = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement> & {
      target: { checked: boolean };
    }
  ) => {
    const { name, value, type, checked } = e.target;
    setForm({
      ...form,
      [name]: (() => {
        switch (type) {
          case "number":
            return +value;
          case "checkbox":
            return checked;
          default:
            return value;
        }
      })(),
    });
  };

  return { form, onChangeForm, setForm };
};
