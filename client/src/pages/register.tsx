import Form from "@/components/forms/form";
import TextField from "@/components/forms/inputs/text-field";
import SubmitButton from "@/components/forms/submit-button";
import TextLink from "@/components/navigation/text-link";

export default function Register() {
  return (
    <Form captionText="Register" className="items-center">
      <TextField label="Username" name="username" />
      <TextField label="Password" name="password" type="password" />
      <TextField label="Repeat password" name="repeatPassword" type="password" />
      <TextLink href="/login">I have an account</TextLink>
      <SubmitButton label="Register"/>
    </Form>
  );
  }